package hw2

import javax.swing.{JButton, JComboBox, JSlider}

case class Tracker(leftSlider: JSlider, sliderTop: JSlider, jComboBox: JComboBox[String], hideButton: JButton){
  val width = 20
  val height = 20

  private var _drawPosit: (Int, Int) = (0, 0)

  def drawPosit = _drawPosit

  def drawPosit_=(point: (Int, Int)) = _drawPosit = point
}