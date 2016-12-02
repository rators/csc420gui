package raflack.sections

import java.awt.CardLayout
import java.awt.event.{MouseEvent, MouseListener}
import javax.swing.JPanel

import flagapp.conversions.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.Group
import raflack.forms.GroupForm
import raflack.model.{GroupsModel, ThreadsLandingModel, ThreadsModel}
import raflack.views.cards.GroupCard

import scala.collection.mutable

class GroupPanel(override val model: GroupsModel, threadsLandingModel: ThreadsLandingModel, groupForm: GroupForm, onCardClick: String => Unit) extends JPanel(new MigLayout()) with ViewPanel {
  this += groupForm ->"dock east"
  addCardsFull()

  def cards: mutable.Buffer[GroupCard] = model.map((data) => GroupCard(data.title, data.description, data.threads.length))

  def groupCards = cards.grouped(4).toIndexedSeq

  def addCardsFull() = {
    for (rowInd <- groupCards.indices) {
      for (colInd <- groupCards(rowInd).indices) {
        val groupCard = groupCards(rowInd)(colInd)
        appendListener(groupCard)
        this += groupCard -> s"cell $colInd $rowInd"
      }
    }
  }

  def appendListener(card: GroupCard): Unit = {
    println("Adding click listeners!")
    val mouseListener = GroupCardClickListener(card, model, threadsLandingModel, onCardClick)
    card.addMouseListener(mouseListener)
  }

  def iterCards(fun: (GroupCard, Int, Int) => Unit) = {
    val cards = groupCards
    for (rowInd <- cards.indices) {
      for (colInd <- cards(rowInd).indices) {
        val groupCard = cards(rowInd)(colInd)
        fun(groupCard, rowInd, colInd)
      }
    }
  }

  def addCards(): Unit = iterCards((card, row, col) => this += card -> s"cell $col $row")

  override def update(): Unit = {
    removeAll()
    addCardsFull()
    this += groupForm -> "dock east"
  }
}

case class GroupCardClickListener(groupCard: GroupCard, groupsModel: GroupsModel, threadsModel: ThreadsLandingModel, onCardClick: String => Unit) extends MouseListener {
  override def mouseExited(e: MouseEvent): Unit = ()

  override def mouseClicked(e: MouseEvent): Unit = {
    val groupClicked: Group = groupsModel.find(_.title.equals(groupCard.name)).get
    threadsModel.clear()
    threadsModel.group = groupClicked
    groupClicked.threads.foreach(threadsModel += _)
    onCardClick(groupCard.name)
  }

  override def mouseEntered(e: MouseEvent): Unit = Unit

  override def mousePressed(e: MouseEvent): Unit = ()

  override def mouseReleased(e: MouseEvent): Unit = ()
}
