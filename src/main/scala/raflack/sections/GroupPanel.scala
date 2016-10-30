package raflack.sections

import java.awt.event.{MouseEvent, MouseListener}
import javax.swing.JPanel

import net.miginfocom.swing.MigLayout
import hw2.SwingImpl._
import raflack.Group
import raflack.views.cards.GroupCard

class GroupPanel(data: List[Group], onCardClick: String => Unit) extends JPanel(new MigLayout()) {
  val cards = data.map((data) => GroupCard(data.title, data.description, data.threads.length))
  val groupCards = cards.grouped(4).toIndexedSeq

  for(rowInd <- groupCards.indices){
    for(colInd <- groupCards(rowInd).indices){
      val groupCard = groupCards(rowInd)(colInd)
      groupCard.addMouseListener(new MouseListener {

        override def mouseExited(e: MouseEvent): Unit = ()

        override def mouseClicked(e: MouseEvent): Unit = {
          onCardClick(groupCard.name)
        }

        override def mouseEntered(e: MouseEvent): Unit = ()

        override def mousePressed(e: MouseEvent): Unit = ()

        override def mouseReleased(e: MouseEvent): Unit = ()
      })
      this += groupCards(rowInd)(colInd) -> s"cell $colInd $rowInd"
    }
  }
}
