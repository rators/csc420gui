package hw2

import java.awt.{Color, Graphics}
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JPanel
import javax.swing.event.{ChangeEvent, ChangeListener}
import hw2.SwingImpl._

case class Circle(tracker: Tracker) extends JPanel {
  setBackground(Color.LIGHT_GRAY)
  val circleThis = this

  val listener = new ChangeListener {
    override def stateChanged(e: ChangeEvent): Unit = {
      circleThis.repaint()
    }
  }

  List(tracker.leftSlider, tracker.sliderTop).foreach(_.addChangeListener(listener))

  tracker.jComboBox.addActionListener(new ActionListener() {
    override def actionPerformed(e: ActionEvent): Unit = {
      circleThis.repaint()
    }
  })

  tracker.hideButton.onClick((e) => {
    val yValue = tracker.leftSlider.getValue.toDouble
    val correctedY = ((HW2.SLIDER_LEFT_MAX - yValue) / HW2.SLIDER_LEFT_MAX) * HW2.CANVAS_HEIGHT
    tracker.drawPosit = (tracker.sliderTop.getValue - 10, correctedY.toInt - 10)
    circleThis.repaint()
  })

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    drawCircle(g)
    drawCrossHair(g)
  }

  def drawCircle(g: Graphics): Unit ={
    g.setColor{
      val buttonText = tracker.hideButton.getText
      buttonText match {
        case "Hide" => tracker.jComboBox.getSelectedItem.toString match {
          case "Red" => Color.RED
          case "Orange" => Color.ORANGE
          case "Yellow" => Color.YELLOW
          case "Green" => Color.GREEN
          case "Blue" => Color.BLUE
          case "Black" => Color.BLACK
        }
        case "Show" => Color.LIGHT_GRAY
      }
    }

    g.fillOval(tracker.drawPosit._1, tracker.drawPosit._2, tracker.width, tracker.height)
  }

  def drawCrossHair(g: Graphics): Unit = {
//    g.setColor(Color.BLACK)
//
//    val correctedY = ((HW2.SLIDER_LEFT_MAX - tracker.leftSlider.getValue.toDouble) / HW2.SLIDER_LEFT_MAX) * HW2.CANVAS_HEIGHT
//    g.fillOval(tracker.sliderTop.getValue, correctedY.toInt, 4, 4)
  }
}