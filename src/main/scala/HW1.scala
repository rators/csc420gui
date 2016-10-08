import java.awt.Dimension
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import hw2.SwingImpl._

object HW1 extends App {

  val countries = Map(
    "Germany" -> "germany", "Ireland" -> "ireland",
    "Poland" -> "poland", "Spain" -> "spain",
    "United Kingdom" -> "UK"
  )

  val flagIcons = scaleImages(countries.keys)

  def scaleImages(imageURLS: Iterable[String]): Map[String, ImageIcon] = {
    val images = imageURLS.map((imgStr) => {
      println(imgStr)
      val flagURL = getClass.getResource("flags/" concat countries(imgStr) concat ".gif")
      val imgFile = new File(flagURL.getFile)
      val image = ImageIO.read(imgFile).getScaledInstance(150, 100, 0)
      val imageIcon = new ImageIcon(image)
      (imgStr, imageIcon)
    })
    images.toMap
  }

  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(300, 300))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val cBoxSelections = countries.keys.toArray
    val countryCBox = new JComboBox[String](cBoxSelections)

    countryCBox.setSelectedIndex(-1)
    countryCBox.setSize(300, 50)
    frame.getContentPane.add(countryCBox)

    val picLabel = new JLabel(new ImageIcon(cBoxSelections.head))
    picLabel.setSize(50, 50)
    frame.add(new JLabel())
    frame.getContentPane.add(picLabel)

    countryCBox.addListener((e) =>
        picLabel.setIcon(flagIcons(countryCBox.getSelectedItem.toString))
    )

    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })
}