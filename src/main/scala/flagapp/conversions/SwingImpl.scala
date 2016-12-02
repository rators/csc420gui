package flagapp.conversions

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import java.awt.{Component, Dimension}
import javax.swing._
import javax.swing.event.{ChangeEvent, ChangeListener}

object SwingImpl {
  implicit def pairToDimension(p: (Int, Int)): Dimension = new Dimension(p._1, p._2)

  implicit class ScalaFrame(f: JFrame) {
    def +=(c: Component) = f.getContentPane.add(c)

    def +=(c: (Component, String)) = f.getContentPane.add(c._1, c._2)
  }

  implicit class ScalaTextField(f: JTextField) {
    def addChangeListener(handler: KeyEvent => Unit) = f.addKeyListener(new KeyAdapter() {
      override def keyPressed(ke: KeyEvent): Unit = {
        handler(ke)
      }
    })
  }


  implicit class ScalaPanel(p: JPanel) {
    def +=(c: Component) = p.add(c)

    def +=(c: (Component, String)) = p.add(c._1, c._2)

    def -=(c: Component) = p.remove(c)
  }

  implicit class ScalaButton(b: JButton) {
    def onClick(handler: ActionEvent => Unit): Unit = {
      b.addActionListener(new ActionListener() {
        override def actionPerformed(e: ActionEvent): Unit = handler(e)
      })
    }
  }

  implicit class ComboBox[T](comboBox: JComboBox[T]) {
    def addListener(eventHandler: ActionEvent => Unit): Unit = {
      comboBox.addActionListener(new ActionListener() {
        override def actionPerformed(e: ActionEvent): Unit = eventHandler(e)
      })
    }
  }

  implicit class ScalaSlider(slider: JSlider) {
    def onSlide(handler: ChangeEvent => Unit): Unit = {
      slider.addChangeListener(new ChangeListener() {
        override def stateChanged(e: ChangeEvent): Unit = handler(e)
      })
    }
  }

}
