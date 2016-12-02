package flagapp

import java.awt.event.{KeyAdapter, KeyEvent, KeyListener}
import java.awt.{CardLayout, Component, Dimension}
import javax.swing.{JComboBox, _}

import flagapp.conversions.SwingImpl._
import flagapp.handler.{DragTransferHandler, ISOKeyListener, Incrementer}
import flagapp.worker.LoadWorker
import net.miginfocom.swing.MigLayout

import scala.collection._
import scala.collection.mutable.ArrayBuffer

object Main extends App {

  val flagIcons = ArrayBuffer()

  var spoof: ImageIcon = _

  private def makeFrame: JFrame = {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(895, 325))
    frame.getContentPane.setPreferredSize(new Dimension(500, 325))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    frame
  }

  private def makeJList(listModel: ListModel[String]): JList[String] = {
    val list = new JList(listModel); //data has type Object[]

    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION)
    list.setLayoutOrientation(JList.HORIZONTAL_WRAP)
    list.setPreferredSize(new Dimension(600, 300))

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

  private def makeTextField(picLabel: JLabel, images: mutable.Map[String, ImageIcon], list: JList[String]) = {
    val textField = new JTextField()
    val fieldListener = ISOKeyListener(textField, picLabel, images, list)
    textField.addKeyListener(fieldListener)

    val transferAction = (icon: ImageIcon) => picLabel.setIcon(icon)

    textField.setTransferHandler(new DragTransferHandler(textField, images, transferAction))
    textField.setPreferredSize(new Dimension(500, 30))
    textField
  }

  def setSizes(mainContent: Component, progressBar: Component, picLabel: Component, countryCBox: Component, listScroller: Component): Unit ={
    mainContent.setPreferredSize(1200, 625)
    progressBar.setPreferredSize(new Dimension(600, 50))
    picLabel.setPreferredSize(new Dimension(600, 300))
    countryCBox.setPreferredSize(new Dimension(600, 50))
    listScroller.setPreferredSize(new Dimension(500, 270))
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
    val menuPanel = new JPanel(new MigLayout())
    val picLabel = new JLabel()
    val loadSplashPanel = new JPanel(new MigLayout("al center center, wrap, gapy 20"))
    val progressBar = new JProgressBar(0, 100)

    val images = mutable.Map[String, ImageIcon]()

    val listModel = new DefaultListModel[String]()
    val list: JList[String] = makeJList(listModel)
    val listScroller = new JScrollPane(list)

    val textField = makeTextField(picLabel, images, list)

    val incrementer = Incrementer(progressBar, list, images, countryCBox, listModel, mainContent)

    LoadWorker(incrementer).execute()

    setSizes(mainContent, progressBar, picLabel, countryCBox, listScroller)

    loadSplashPanel += progressBar -> "span"
    menuPanel += textField -> "span"
    menuPanel += picLabel -> "dock east"
    mainContent += loadSplashPanel -> "load"
    mainContent += menuPanel -> "loaded"
    menuPanel += listScroller -> "span"

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



