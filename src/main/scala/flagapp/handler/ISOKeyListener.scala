package flagapp.handler

import java.awt.event.{KeyAdapter, KeyEvent}
import javax.swing._

import scala.collection.mutable

/**
  * Key listener that updated the current image as the text field is updated.
  *
  * @param textField
  * The text field to update.
  * @param picLabel
  * The picture label component that contains the currently visible flag.
  * @param images
  * The map of images available.
  * @param list
  * The JList component that displays all country isos.
  *
  */
case class ISOKeyListener(textField: JTextField, picLabel: JLabel, images: mutable.Map[String, ImageIcon], list: JList[String]) extends KeyAdapter {

  def scaleImage(i: ImageIcon): ImageIcon = new ImageIcon(i.getImage.getScaledInstance(600, 300, java.awt.Image.SCALE_SMOOTH))

  override def keyPressed(e: KeyEvent): Unit = {
    val transferAction = (icon: ImageIcon) => picLabel.setIcon(icon)

    images get textField.getText match {
      case None => ()
      case Some(image) =>
        list.getModel.asInstanceOf[DefaultListModel[String]].contains(textField.getText) match {
          case true => list.setSelectedIndex(list.getModel.asInstanceOf[DefaultListModel[String]].indexOf(textField.getText))
          case _ => ()
        }
        transferAction(scaleImage(image))
    }
  }
}
