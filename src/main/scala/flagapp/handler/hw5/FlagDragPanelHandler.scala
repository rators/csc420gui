package flagapp.handler.hw5

import java.awt.Component
import java.awt.datatransfer.DataFlavor._
import java.awt.datatransfer.Transferable
import javax.swing.{ImageIcon, TransferHandler}

import flagapp.model.{FlagModel, XYModel}

import scala.collection.mutable.ListBuffer

class FlagDragPanelHandler(flagModel: FlagModel, xyModel: XYModel, imageMap: scala.collection.mutable.Map[String, ImageIcon]) extends TransferHandler {
  val boundedComps = ListBuffer[Component]()

  def <~(c: Component): FlagDragPanelHandler = {
    boundedComps += c
    this
  }

  override def canImport(support: TransferHandler.TransferSupport): Boolean = support.isDataFlavorSupported(stringFlavor)

  override def importData(support: TransferHandler.TransferSupport): Boolean = {
    canImport(support) match {
      case false => false
      case true => importAction(support)
    }
  }

  private def importAction(support: TransferHandler.TransferSupport): Boolean = {
    val transferable: Transferable = support.getTransferable
    val data: String = transferable.getTransferData(stringFlavor).asInstanceOf[String]

    imageMap get data match {
      case None => false
      case Some(image) =>
        flagModel.imageIcon = Some(image)
        boundedComps.foreach(_.repaint)
        true
    }
  }

  def makeImage(imageIcon: ImageIcon) = {
    new ImageIcon(imageIcon.getImage.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH))
  }

}