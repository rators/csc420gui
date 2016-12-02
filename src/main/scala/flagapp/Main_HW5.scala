package flagapp

import java.awt._
import javax.swing.{JComboBox, _}

import flagapp.conversions.SwingImpl._
import flagapp.handler._
import flagapp.handler.hw5.FlagDragPanelHandler
import flagapp.model.{FlagModel, XYModel}
import flagapp.panel.DrapAndDropPanel
import flagapp.worker.LoadWorker
import net.miginfocom.swing.MigLayout

import scala.collection._
import scala.collection.mutable.ArrayBuffer

object Main_HW5 extends App {

  val flagIcons = ArrayBuffer()

  var spoof: ImageIcon = _

  private def makeFrame: JFrame = {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(1280, 500))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame
  }

  private def makeJList(listModel: ListModel[String]): JList[String] = {
    val list = new JList(listModel); //data has type Object[]

    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION)
    list.setLayoutOrientation(JList.HORIZONTAL_WRAP)
    list.setDragEnabled(true)
    list.setDropMode(DropMode.INSERT)
    list
  }

  private def makeDropDown = {
    val cBoxSelections = new Array[String](0)

    val countryCBox = new JComboBox[String](cBoxSelections)

    countryCBox.setSelectedIndex(-1)
    countryCBox.setSize(300, 50)
    countryCBox
  }

  private def makeTextField(picLabel: JPanel, images: mutable.Map[String, ImageIcon], list: JList[String]) = {
    val textField = new JTextField()

    textField.setPreferredSize(new Dimension(500, 30))
    textField
  }

  private def JSlider(model: BoundedRangeModel): JSlider = new JSlider(model)

  private def ListModel(endBound: Int) = new DefaultBoundedRangeModel(0, 0, 0, endBound)

  def setSizes(mainContent: Component, progressBar: Component, dragPanel: Component, countryCBox: Component, listScroller: Component): Unit = {
    mainContent.setPreferredSize(1600, 1600)
    progressBar.setPreferredSize(new Dimension(1600, 1600))
    listScroller.setPreferredSize(new Dimension(800, 800))
    dragPanel.setPreferredSize(800, 800)
  }


  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = makeFrame
    val mainContent = new JPanel(new CardLayout())

    val countryCBox = makeDropDown
    val menuPanel = new JPanel(new MigLayout("wrap", "[grow][grow]", "[grow]"))

    val loadSplashPanel = new JPanel(new MigLayout("wrap", "[grow]", "[grow]"))
    val progressBar = new JProgressBar(0, 100)

    val images = mutable.Map[String, ImageIcon]()

    val dragPanel = new DrapAndDropPanel(images)

    val listModel = new DefaultListModel[String]()
    val list: JList[String] = makeJList(listModel)

    val listScroll = new JScrollPane(list)

    val listScroller = new JPanel(new MigLayout("wrap", "[grow]", "[grow]"))
    listScroller += listScroll -> "grow"

    val incrementer = Incrementer(progressBar, list, images, countryCBox, listModel, mainContent)

    LoadWorker(incrementer).execute()

    setSizes(mainContent, progressBar, dragPanel, countryCBox, listScroller)

    loadSplashPanel += progressBar -> "span"

    menuPanel += dragPanel -> "grow"
    menuPanel += listScroller -> "grow"

    mainContent += loadSplashPanel -> "load"
    mainContent += menuPanel -> "loaded"

    frame.getContentPane.add(mainContent)
    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })

}





