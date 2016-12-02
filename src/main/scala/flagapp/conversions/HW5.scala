package flagapp.conversions

import javax.swing.{BoundedRangeModel, DefaultBoundedRangeModel, JSlider}

/**
  * Created by rtorres on 12/1/16.
  */
object HW5 {

  def JSlider(model: BoundedRangeModel): JSlider = new JSlider(model)

  def ListModel(endBound: Int) = new DefaultBoundedRangeModel(0, 0, 0, endBound)

}
