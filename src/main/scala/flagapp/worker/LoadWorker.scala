package flagapp.worker

import java.io.ByteArrayInputStream
import javax.imageio.ImageIO
import javax.swing.{ImageIcon, SwingWorker}

import akka.actor.ActorSystem
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import flagapp.handler.Incrementer
import spray.can.Http
import spray.http.HttpMethods._
import spray.http.{HttpRequest, HttpResponse, Uri}
import spray.json._

import scala.collection.JavaConversions._
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

/**
  * The load worker used to concurrently update the dropdown.
  *
  * @param incrementer
  * The action to call when a chunk of the task has been completed.
  */
case class LoadWorker(incrementer: Incrementer) extends SwingWorker[List[(ImageIcon, String)], (ImageIcon, String)] {
  implicit val system: ActorSystem = ActorSystem()
  implicit val timeout: Timeout = Timeout(15.seconds)

  /**
    * Calls the countries api and returns the response containing the
    * information for the list of countries.
    *
    * @return
    * The response from geo-gnos.
    */
  def getNamesData: Future[HttpResponse] = {
    // implicit execution context
    val nameUri = "http://www.geognos.com/api/en/countries/info/all.json"
    val response: Future[HttpResponse] =
      (IO(Http) ? HttpRequest(GET, Uri(nameUri))).mapTo[HttpResponse]
    response
  }

  /**
    * Converts a response into the list of image strings for all countries.
    *
    * @param res
    * The response from geo-gnos.
    * @return
    * The list of country iso2 ids.
    */
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

  /**
    * Gets the image data associated with a country iso id.
    *
    * @param iso2Name
    * An iso id for a country.
    * @return
    * The response from the server, containing the flag image.
    */
  def getImageData(iso2Name: String): Future[HttpResponse] = {
    val uriStr = (s: String) => s"http://www.geognos.com/api/en/countries/flag/$s.png"
    val response: Future[HttpResponse] =
      (IO(Http) ? HttpRequest(GET, Uri(uriStr(iso2Name)))).mapTo[HttpResponse]
    response
  }

  /**
    * Converts a response into an image.
    *
    * @param res
    * A response from the server.
    * @param andThen
    * The action to call after an image icon is created.
    * @return
    * The image icon from a response.
    */
  def resToImage(res: HttpResponse)(andThen: ImageIcon => Unit): ImageIcon = {
    val inputStream = new ByteArrayInputStream(res.entity.data.toByteArray)
    val image = ImageIO.read(inputStream)
    val icon = new ImageIcon(image)
    andThen(icon)
    icon
  }

  /**
    * Gets the image associated with the iso2name.
    *
    * @param iso2name
    * The iso id for a country.
    * @param andThen
    * The action to trigger after an imageicon is obtained.
    * @return
    * A flag image icon.
    */
  def getImage(iso2name: String)(andThen: ImageIcon => ImageIcon): ImageIcon = {
    val imageData: Future[HttpResponse] = getImageData(iso2name)
    val imageRes: HttpResponse = Await.result(imageData, 5000 millis)

    resToImage(imageRes) { (icon) =>
      publish((icon, iso2name))
    }
  }

  /**
    * @inheritdoc
    */
  override def process(chunks: java.util.List[(ImageIcon, String)]): Unit = {
    chunks.foreach((p) => incrementer.incrementAction(p._1, p._2))
  }

  /**
    * @inheritdoc
    */
  override def doInBackground(): List[(ImageIcon, String)] = {
    // get the names for all the flags
    val namesResponse: HttpResponse = Await.result(getNamesData, 5000 millis)

    val namesMaps: List[String] = resToNameMap(namesResponse).toList.slice(0, 100)

    def convertAction: String => ImageIcon = (name) => {
      getImage(name)((icon: ImageIcon) => incrementer.incrementAction(icon, name))
    }

    namesMaps.map((p) => (convertAction(p), p))
  }

}
