package flagapp.model

import javax.swing.ImageIcon

class FlagModel(var _imageIcon: Option[ImageIcon], private val xYModel: XYModel){
  def posit = xYModel.getCoOrds

  def imageIcon_=(imageIcon: Option[ImageIcon]) = {
    this._imageIcon = imageIcon
  }

  def imageIcon = _imageIcon
}
