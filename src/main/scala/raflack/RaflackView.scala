package raflack

import java.awt.{CardLayout, Dimension}
import java.awt.event.{MouseEvent, MouseListener}
import java.io.File
import javax.imageio.ImageIO
import javax.swing._

import hw2.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.forms.{GroupForm, ThreadForm}
import raflack.sections.{GroupPanel, ThreadPanel, UserMenu}
import raflack.views.cards.GroupCard

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

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
    val dbRoot = db.getRoot
    val root = dbRoot.head
    root.threads += Thread("Who is going to win this election?", ArrayBuffer.empty)
    val threadForm = new ThreadForm()
    val threadCardPanel = new ThreadPanel(root, root.threads.toList, threadForm)
    threadCardPanel += threadForm -> "dock east"

    val groupCardPanel = new GroupPanel(dbRoot, (title) => {
      println(title)
      println(dbRoot.find(_.title.equals(title)))
      threadCardPanel.show(dbRoot.find(_.title.equals(title)).get)
      val item: String = "thread"
      val cl = contextPanel.getLayout.asInstanceOf[CardLayout]
      println(item)
      cl.show(contextPanel, item)
      frame.revalidate()
      contextPanel.validate()
      frame.repaint()
      contextPanel.repaint()
      contextPanel.revalidate()
    })

    contextPanel.add("group", groupCardPanel)
    contextPanel.add("thread", threadCardPanel)

    val groupFormPanel = new GroupForm()
    groupFormPanel.onSubmit((title, description) => {
      println(s"The title is $title, the description is $description")
      val g = Future {
        db.addGroup(title, description)
      }
      g.onComplete {
        case Success(group) =>
          SwingUtilities.invokeLater(new Runnable {
            override def run(): Unit = {}

            groupCardPanel += GroupCard(group.title, group.description, group.threads.length)
            frame.revalidate()
          })
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
    groupCardPanel += groupFormPanel -> "dock east"
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
