package raflack

import java.awt.{CardLayout, Dimension}
import javax.swing._

import flagapp.conversions.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.forms.{GroupForm, ThreadLandingForm}
import raflack.model.{GroupsModel, ThreadsLandingModel}
import raflack.sections.{GroupPanel, ThreadLandingPanel, UserMenu}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Success

object RaflackView extends App {
  val db = new RaflackDB()
  val data = db.getRoot

  /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from the
    * event-dispatching thread.
    */
  private def createAndShowGUI() {
    val frame = new JFrame("GUI Country Flag Assignment")
    frame.setPreferredSize(new Dimension(500, 500))
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    val userMenu = new UserMenu()

    val mainPanel = new JPanel(new MigLayout("", "20[]"))
    val pane = new JScrollPane(userMenu)


    val contextPanel = new JPanel(new CardLayout())
    def dbRoot = db.getRoot
    val root = dbRoot.head
    root.threads += RThread("Who is going to win this election?", ArrayBuffer.empty)

    val threadsList = root.threads
    val threadsModel: ThreadsLandingModel = new ThreadsLandingModel(None, threadsList)

    val threadForm = new ThreadLandingForm(threadsModel)
    val groupFormPanel = new GroupForm()

    val threadCardPanel = new ThreadLandingPanel(threadsModel, threadForm)
    threadCardPanel += threadForm -> "dock east"
    threadsModel <~ threadCardPanel

    val groupsModel = new GroupsModel(dbRoot)

    val groupCardPanel = new GroupPanel(groupsModel, threadsModel, groupFormPanel, (title) => {
      val item: String = "thread"
      val cl = contextPanel.getLayout.asInstanceOf[CardLayout]
      cl.show(contextPanel, item)
    })

    groupsModel <~ groupCardPanel

    contextPanel.add("group", groupCardPanel)
    contextPanel.add("thread", threadCardPanel)

    groupFormPanel.onSubmit((title, description) => {
      println(s"The title is $title, the description is $description")

      Future {
        db.addGroup(title, description)
      }.onComplete {
        case Success(group) =>
          SwingUtilities.invokeLater(new Runnable{def run() = groupsModel += group})
        case _ => ???
      }
    })

    val mainPanelTitle = new JLabel("<html><h1>Raflack!</h1></html>")

    val backButton = new JButton("Back")

    backButton.onClick((e) => {
      val item: String = "group"
      val cl = contextPanel.getLayout.asInstanceOf[CardLayout]
      println(item)
      cl.show(contextPanel, item)
      frame.revalidate()
      contextPanel.validate()
      frame.repaint()
      contextPanel.repaint()
      contextPanel.revalidate()
    })

    mainPanel += backButton -> "dock south"
    mainPanel += contextPanel
    mainPanel += mainPanelTitle -> "dock north"
    mainPanel += pane -> "dock west"

    mainPanel.setPreferredSize(1920, 1080)

    frame += mainPanel
    frame.setPreferredSize(1920, 1080)
    frame.pack()
    frame.setVisible(true)
  }

  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    override def run() {
      createAndShowGUI()
    }
  })
}
