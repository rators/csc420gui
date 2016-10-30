import java.awt.{Dimension, Image}
import java.io.{ByteArrayInputStream, File}
import java.util
import javax.imageio.ImageIO
import javax.swing._

import akka.actor.ActorSystem
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import hw2.SwingImpl._
import net.miginfocom.swing.MigLayout
import spray.can.Http
import spray.http.HttpMethods._
import spray.http._
import spray.json._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

import hw2.SwingImpl._
import scala.util.{Failure, Success}

case class LoadWorker(incrementAction: (ImageIcon, String, Int) => Unit) extends SwingWorker[List[ImageIcon], (String, ImageIcon)] {
  implicit val system: ActorSystem = ActorSystem()
  implicit val timeout: Timeout = Timeout(15.seconds)

  def getNamesData: Future[HttpResponse] = {
    // implicit execution context
    val nameUri = "http://www.geognos.com/api/en/countries/info/all.json"
    val response: Future[HttpResponse] =
      (IO(Http) ? HttpRequest(GET, Uri(nameUri))).mapTo[HttpResponse]
    response
  }

  def resToNameMap(res: HttpResponse): Iterable[String] = {
    val countryData = res
      .entity
      .data
      .asString
      .parseJson
      .asJsObject
      .getFields("Results")
      .head
      .asJsObject
      .fields

    countryData.keys
  }

  def getImageData(iso2Name: String): Future[HttpResponse] = {
    val uriStr = (s: String) => s"http://www.geognos.com/api/en/countries/flag/$s.png"
    val response: Future[HttpResponse] =
      (IO(Http) ? HttpRequest(GET, Uri(uriStr(iso2Name)))).mapTo[HttpResponse]
    response
  }

  def resToImage(res: HttpResponse)(andThen: ImageIcon => Unit): ImageIcon = {
    val inputStream = new ByteArrayInputStream(res.entity.data.toByteArray)
    val image = ImageIO.read(inputStream)
    val icon = new ImageIcon(image)
    andThen(icon)
    icon
  }

  def getImage(iso2name: String)(andThen: ImageIcon => Unit): ImageIcon = {
    println(iso2name)
    val imageData = getImageData(iso2name)
    val imageRes: HttpResponse = Await.result(imageData, 5000 millis)

    resToImage(imageRes) { (icon) =>
      println("image retrieved!")
      publish((iso2name, icon))
      andThen(icon)
    }
  }

  override def process(chunks: util.List[(String, ImageIcon)]): Unit = {

  }

  override def doInBackground(): List[ImageIcon] = {
    // get the names for all the flags
    val namesResponse: HttpResponse = Await.result(getNamesData, 5000 millis)

    val namesMaps: List[String] = resToNameMap(namesResponse).toList.slice(0, 100)

    def convertAction: String => ImageIcon = (name) => {
      getImage(name)(incrementAction(_, name, (namesMaps.indexOf(name) + 1 ) * 10))
    }

    namesMaps.map(convertAction)
  }
}

object HW1 extends App {

  val flagIcons = ArrayBuffer()

  var spoof: ImageIcon = _

  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(600, 600))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val mainContent = new JPanel(new MigLayout())

    val cBoxSelections = new Array[String](0)
    val countryCBox = new JComboBox[String](cBoxSelections)

    countryCBox.setSelectedIndex(-1)
    countryCBox.setSize(300, 50)
    frame.getContentPane.add(mainContent)

    val picLabel = new JLabel()
    picLabel.setSize(50, 50)

    val images = scala.collection.mutable.Map[String, ImageIcon]()

    countryCBox.addListener((e) => {
      val selected = countryCBox.getSelectedItem.toString
      val imageIcon = images(selected)
      picLabel.setIcon(imageIcon)
    })

    val progressBar = new JProgressBar(0, 100)

    val incrementAction = (i: ImageIcon, name: String, progress: Int) => {
      progressBar.setValue(progress)

      images += name -> i
      countryCBox.addItem(name)
    }

    mainContent += progressBar -> "cell 0 0 2 2"
    mainContent += picLabel -> "cell 0 2 2 2"
    mainContent += countryCBox -> "cell 0 4 2 2"

    LoadWorker(incrementAction).execute()

    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })
}