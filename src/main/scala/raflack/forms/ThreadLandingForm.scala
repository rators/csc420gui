package raflack.forms

/**
  * Thread form component
  */
import javax.swing.{JButton, JLabel, JPanel, JTextField}

import flagapp.conversions.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.RThread
import raflack.model.ThreadsModel

import scala.collection.mutable.ArrayBuffer

class ThreadLandingForm(threadModel: ThreadsModel) extends JPanel(new MigLayout()) {
  type SubmitAction = (String, String) => Unit
  // the form for making new groups
  val title = new JTextField()
  val titleLabel = new JLabel("Title:")
  val description = new JTextField()
  val descriptionLabel = new JLabel("")
  val formTitle = new JLabel("<html><h2>Create Thread</h2></html>")

  title.setPreferredSize(200, 30)
  description.setPreferredSize(300, 30)
  this += formTitle -> "dock north"
  this += titleLabel -> "cell 0 0"
  this += title -> "cell 1 0"
  this += descriptionLabel -> "cell 0 1"
  this += description -> "cell 1 1"

  val buttonPanel = new JPanel(new MigLayout())
  val addGroupButton = new JButton("Confirm")
  buttonPanel += addGroupButton -> "dock east"
  this += buttonPanel -> "dock south"

  addGroupButton.onClick((e) => {
    threadModel += RThread(title.getText, ArrayBuffer.empty)
  })
}