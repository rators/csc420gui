package raflack.sections

import javax.swing.JPanel

import net.miginfocom.swing.MigLayout
import raflack.model.Model

/**
  *
  */
trait ViewPanel extends JPanel {
  setLayout(new MigLayout)

  def update(): Unit

  def model: Model
}
