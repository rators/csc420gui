package raflack

import java.awt.Dimension
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import hw2.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.views.cards.RoundedPane

case class DescriptionCard(title: String, description: String, count: Int) extends RoundedPane(new MigLayout()) {
  val titleLabel = new JLabel(s"<html><h3><b>$title</b></h3></html>")
  val descriptionLabel = new JLabel(s"<html><h4>$description</h4><html>")
  val posts = new JLabel(s"<html><h5>Threads: $count</h5></html>")
  this += titleLabel -> "cell 0 0"
  this += descriptionLabel -> "cell 0 1"
  this += posts -> "cell 0 2"
}

object RaflackView extends App {
  val db = new RaflackDB()
  val data = db.getRoot

  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(500, 500))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

//    val cBoxSelections = data.map(_.title).toArray
//    val countryCBox = new JComboBox[String](cBoxSelections)
//
//    countryCBox.setSelectedIndex(-1)
//    countryCBox.setSize(300, 50)
//    frame += countryCBox
//
//    val picLabel = new JLabel(cBoxSelections.head)
//    picLabel.setSize(50, 50)
//    frame += picLabel

    val mainPanel = new JPanel(new MigLayout())

    val descCard = DescriptionCard(
      "Politics",
      "Discuss all things politics.",
      50
    )

    mainPanel += descCard -> "cell 0 0"

//    countryCBox.addListener((e) =>
//      picLabel.setText(countryCBox.getSelectedItem.toString)
//    )
    frame += mainPanel
    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })
}