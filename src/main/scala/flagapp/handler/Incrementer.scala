package flagapp.handler

import java.awt.CardLayout
import javax.swing._

import scala.collection.mutable

/**
  * The imcrementer
  *
  * @param progressBar
  * @param list
  * @param images
  * @param countryCBox
  * @param listModel
  * @param mainContent
  */
case class Incrementer(
                        progressBar: JProgressBar, list: JList[String],
                        images: mutable.Map[String, ImageIcon],
                        countryCBox: JComboBox[String],
                        listModel: DefaultListModel[String],
                        mainContent: JPanel
                      ) {

  /**
    * The method called to increment the progress bar.
    * @param image
    *              The image to add to the jlist.
    * @param name
    *             The name of the country (iso).
    * @return
    *         The image added to the list of available images.
    */
  def incrementAction(image: ImageIcon, name: String) = {
    val prevValue = progressBar.getValue
    progressBar.setValue(prevValue + 1)
    // add image to the images map
    images += name -> image

    listModel.addElement(name)
    // set the selected value in the index of the current value
    list.setSelectedIndex(prevValue)

    if (prevValue + 1 == 100) {
      val cardLayout = mainContent.getLayout.asInstanceOf[CardLayout]
      cardLayout.show(mainContent, "loaded")
    }
    image
  }

}
