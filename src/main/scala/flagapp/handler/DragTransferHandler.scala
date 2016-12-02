package flagapp.handler

import java.awt.datatransfer.DataFlavor._
import java.awt.datatransfer.Transferable
import javax.swing.{ImageIcon, JTextField, TransferHandler}

class DragTransferHandler(jTextBox: JTextField, imageMap: scala.collection.mutable.Map[String, ImageIcon], setAction: ImageIcon => Unit) extends TransferHandler {

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

    jTextBox.setText(data)

    imageMap get data match {
      case None => ()
      case Some(image) => setAction(makeImage(image))
    }

    true
  }

  def makeImage(imageIcon: ImageIcon) = {
    new ImageIcon(imageIcon.getImage.getScaledInstance(600, 300, java.awt.Image.SCALE_SMOOTH))
  }

}