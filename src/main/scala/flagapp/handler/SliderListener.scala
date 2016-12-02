package flagapp.handler

import java.awt.Component
import javax.swing.{BoundedRangeModel, JSlider}
import javax.swing.event.{ChangeEvent, ChangeListener}

import scala.collection.mutable.ListBuffer

/**
  *
  */
case class SliderListener(xSlider: JSlider, ySlider: JSlider) extends ChangeListener {
  val boundedComps = ListBuffer[Component]()

  def <~(c: Component): SliderListener = {
    boundedComps += c
    this
  }

  override def stateChanged(e: ChangeEvent): Unit = {
    val model = e.getSource.asInstanceOf[BoundedRangeModel]
    println(xSlider.getValue -> ySlider.getValue)
    boundedComps.foreach(_.repaint)
  }
}
