package raflack.sections

import java.awt.event.{MouseEvent, MouseListener}
import javax.swing.JPanel

import flagapp.conversions.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.forms.ThreadLandingForm
import raflack.model.ThreadsModel
import raflack.views.cards.ThreadCard

class ThreadLandingPanel(override val model: ThreadsModel, form: ThreadLandingForm) extends JPanel(new MigLayout()) with ViewPanel {
  def cards = model.map((t) => ThreadCard(t.name, "John Smith", t.comments.length))
  def threadCards = cards.grouped(4).toIndexedSeq

  this += form -> "dock east"

  insertCards()

  def insertCards(): Unit = {
    for (rowInd <- threadCards.indices) {
      for (colInd <- threadCards(rowInd).indices) {
        val groupCard = threadCards(rowInd)(colInd)
        groupCard.addMouseListener(new MouseListener {

          override def mouseExited(e: MouseEvent): Unit = ()

          override def mouseClicked(e: MouseEvent): Unit = {
            println("Clicked on card!")
          }

          override def mouseEntered(e: MouseEvent): Unit = ()

          override def mousePressed(e: MouseEvent): Unit = ()

          override def mouseReleased(e: MouseEvent): Unit = ()
        })
        this += threadCards(rowInd)(colInd) -> s"cell $colInd $rowInd"
      }
    }
  }

  def update(): Unit = {
    removeAll()
    this += form -> "dock east"
    insertCards()
  }

}
