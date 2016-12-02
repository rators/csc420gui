package flagapp.handler

import java.awt._
import javax.swing.JPanel

import flagapp.model.{FlagModel, XYModel}

/**
  *
  */
class FlagDragPanel(val xyModel: XYModel, val flagModel: FlagModel, layout: LayoutManager) extends JPanel(layout) {
  override def paintComponent(g: Graphics) {
    super.paintComponent(g)
    val g2: Graphics2D = g.asInstanceOf[Graphics2D]
    val coOrds = xyModel.getCoOrds
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    g2.setColor(Color.red)

    flagModel.imageIcon match {
      case Some(imageIcon) =>
        val xScale: Double = this.getWidth.toDouble * (flagModel.posit.x / 100d)
        val yScale: Double = this.getHeight.toDouble * (Math.abs(flagModel.posit.y - 100) / 100d)
        g2.drawImage(imageIcon.getImage, xScale.toInt - 80, yScale.toInt - 40, null)
      case None =>
        val xScale: Double = this.getWidth.toDouble * (coOrds.x / 100d)
        val yScale: Double = this.getHeight.toDouble * (Math.abs(coOrds.y - 100) / 100d)

        g2.drawRect(xScale.toInt, yScale.toInt, 2, 5)
        g2.drawRect(xScale.toInt, yScale.toInt + 10, 2, 5)

        g2.drawRect(xScale.toInt - 10, yScale.toInt + 5, 5, 2)
        g2.drawRect(xScale.toInt + 6, yScale.toInt + 5, 5, 2)
    }
  }
}
