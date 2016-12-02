package raflack.forms

import javax.swing.{JButton, JLabel, JPanel, JTextField}

import net.miginfocom.swing.MigLayout
import flagapp.conversions.SwingImpl._

class GroupForm extends JPanel(new MigLayout()) {
  type SubmitAction = (String, String) => Unit
  // the form for making new groups
  val title = new JTextField()
  val titleLabel = new JLabel("Title:")
  val description = new JTextField()
  val descriptionLabel = new JLabel("Description:")
  val formTitle = new JLabel("<html><h2>Create Group</h2></html>")

  title.setPreferredSize(200, 30)
  description.setPreferredSize(300, 30)
  description.setEnabled(true)
  this += formTitle -> "dock north"
  this += titleLabel -> "cell 0 0"
  this += title -> "cell 1 0"
  this += descriptionLabel -> "cell 0 1"
  this += description -> "cell 1 1"

  val buttonPanel = new JPanel(new MigLayout())
  val addGroupButton = new JButton("Confirm")
  buttonPanel += addGroupButton -> "dock east"
  this += buttonPanel -> "dock south"

  def onSubmit(action: SubmitAction) = addGroupButton.onClick((e) => action(title.getText(), description.getText))
}
