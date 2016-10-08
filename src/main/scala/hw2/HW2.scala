package hw2

import java.awt.Color
import javax.swing._

import net.miginfocom.swing.MigLayout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import hw2.SwingImpl._

object HW2 extends App {

  lazy val SLIDER_LEFT_MAX = 100d
  lazy val CANVAS_WIDTH = 680
  lazy val CANVAS_HEIGHT = 480

  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.getContentPane.setLayout(new MigLayout(
      "",
      "0[]0[]",
      "0[]0[]0[]"
    ))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val sliderTop = new JSlider(0, CANVAS_WIDTH)

    sliderTop.setOrientation(0)
    sliderTop.setPreferredSize(700, 0)

    val sliderLeft = new JSlider(0, 100)
    sliderLeft.setOrientation(1)
    sliderLeft.setPreferredSize(0, 500)

    val colors = Array("Red", "Orange", "Yellow", "Green", "Blue", "Black")
    val colorSelections = new JComboBox[String](colors)
    val button = new JButton("Show")

    val canvasPanel = new JPanel(new MigLayout("", "0[]0", "0[]0"))
    canvasPanel.setPreferredSize(700, 500)
    canvasPanel.setBackground(Color.WHITE)
    canvasPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10 ,10, Color.DARK_GRAY))

    val circlePanel = Circle(Tracker(sliderLeft, sliderTop, colorSelections, button))
    circlePanel.setPreferredSize(CANVAS_WIDTH, CANVAS_HEIGHT)

    val inputPanel = makeInputPanel(colorSelections, button)

    canvasPanel += circlePanel -> "grow"
    frame += canvasPanel -> "cell 1 1 1 1"
    frame += sliderLeft -> "cell 0 1 1 1"
    frame += inputPanel -> "cell 1 3 1 1"
    frame += sliderTop -> "cell 1 2 1 1"

    sliderTop.onSlide((e) => {
      Future {
        canvasPanel.repaint()
      }
    })

    sliderLeft.onSlide((e) => {
      Future {
        canvasPanel.repaint()
      }
    })

    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })

  private def makeInputPanel(countryCBox: JComboBox[String], button: JButton): JPanel = {
    val inputPanel = new JPanel(new MigLayout())
    inputPanel.setPreferredSize(270, 60)

    //make color selection box, allows user to determine color for the box.
    countryCBox.setPreferredSize(120, 50)

    //make show or hide button, allows user to show or hide a circle on the canvas
    button.setPreferredSize(100, 50)

    button.onClick{(e) => {
      button.setText {
        button.getText match {
          case "Show" => "Hide"
          case "Hide" => "Show"
        }
      }
    }}

    inputPanel += countryCBox
    inputPanel += button
    inputPanel
  }
}
