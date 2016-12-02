package flagapp.panel

import java.awt.Color
import javax.swing._

import flagapp.handler.{FlagDragPanel, SliderListener}
import flagapp.handler.hw5.FlagDragPanelHandler
import flagapp.model.{FlagModel, XYModel}
import net.miginfocom.swing.MigLayout
import flagapp.conversions.SwingImpl._
import scala.collection._

class DrapAndDropPanel(imageMap: mutable.Map[String, ImageIcon]) extends JPanel(new MigLayout("wrap", "[grow]", "[grow]")) {
  val yModel = ListModel(100)
  val xModel = ListModel(100)
  val xyModel = XYModel(xModel, yModel)
  val flagModel = new FlagModel(None, xyModel)
  val dragPanel = new FlagDragPanel(xyModel, flagModel, new MigLayout())

  dragPanel.setBackground(Color.LIGHT_GRAY)

  val transferHandler = new FlagDragPanelHandler(flagModel, xyModel, imageMap)
  dragPanel.setTransferHandler(transferHandler)
  transferHandler <~ dragPanel

  val leftSlider = JSlider(yModel)
  val leftSliderPanel = new JPanel(new MigLayout("wrap", "[grow]", "[grow]"))
  leftSliderPanel += leftSlider -> "east, pad 20 0 -20 0"

  val rightSlider = JSlider(yModel)
  val rightSliderPanel = new JPanel(new MigLayout("wrap", "[grow]", "[grow]"))
  rightSliderPanel += rightSlider -> "west, pad 20 0 -20 0"

  val topSlider = JSlider(xModel)
  val topSliderPanel = new JPanel(new MigLayout("wrap", "[grow][grow]", "[grow][grow]"))
  topSliderPanel += topSlider -> "north, grow"

  val bottomSlider = JSlider(xModel)
  val bottomSliderPanel = new JPanel(new MigLayout("wrap", "[grow][grow]", "[grow][grow]"))
  bottomSliderPanel += bottomSlider -> "south, grow"

  leftSlider.setOrientation(SwingConstants.VERTICAL)
  rightSlider.setOrientation(SwingConstants.VERTICAL)

  val sliderListener = SliderListener(leftSlider, topSlider)

  yModel.addChangeListener(sliderListener)
  xModel.addChangeListener(sliderListener)

  sliderListener <~ dragPanel

  this += dragPanel -> "grow"
  this += rightSliderPanel -> "dock west"
  this += leftSliderPanel -> "dock east"
  this += topSliderPanel -> "dock north"
  this += bottomSliderPanel -> "dock south"

  def JSlider(model: BoundedRangeModel): JSlider = new JSlider(model)

  def ListModel(endBound: Int) = new DefaultBoundedRangeModel(0, 0, 0, endBound)


}
