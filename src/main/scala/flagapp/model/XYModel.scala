package flagapp.model

import javax.swing.DefaultBoundedRangeModel

case class CoOrds(x: Int, y: Int)

/**
  * The model for the xy co-ordinates.
  * @param xModel
  *               The model for the
  * @param yModel
  */
case class XYModel(xModel: DefaultBoundedRangeModel, yModel: DefaultBoundedRangeModel) {
  def getCoOrds = CoOrds(xModel.getValue, yModel.getValue)
}
