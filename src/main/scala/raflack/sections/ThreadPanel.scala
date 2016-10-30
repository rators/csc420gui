package raflack.sections

import java.awt.event.{MouseEvent, MouseListener}
import javax.swing.JPanel

import hw2.SwingImpl._
import net.miginfocom.swing.MigLayout
import raflack.forms.ThreadForm
import raflack.views.cards.{GroupCard, ThreadCard}
import raflack.{Group, Thread}
class ThreadPanel(group: Group, data: List[Thread], form: ThreadForm) extends JPanel(new MigLayout()) {
  val cards = data.map((t) => ThreadCard(t.name, "John Smith", t.comments.length))
  val groupCards = cards.grouped(4).toIndexedSeq
  form.panel = this
  for(rowInd <- groupCards.indices){
    for(colInd <- groupCards(rowInd).indices){
      println(s"$rowInd, $colInd")
      val groupCard = groupCards(rowInd)(colInd)
      groupCard.addMouseListener(new MouseListener {

        override def mouseExited(e: MouseEvent): Unit = ()

        override def mouseClicked(e: MouseEvent): Unit = {
          println("Clicked on card!")
        }

        override def mouseEntered(e: MouseEvent): Unit = ()

        override def mousePressed(e: MouseEvent): Unit = ()

        override def mouseReleased(e: MouseEvent): Unit = ()
      })
      this += groupCards(rowInd)(colInd) -> s"cell $colInd $rowInd"
    }
  }

  def show(group: Group): Unit = {
    form.parentGroup = group
    this.removeAll()
    this.revalidate()
    this += form -> "dock east"
    val cards = group.threads.map((t) => ThreadCard(t.name, "John Smith", t.comments.length))
    val threadCards = cards.grouped(4).toIndexedSeq

    for(rowInd <- threadCards.indices){
      for(colInd <- threadCards(rowInd).indices){
        println(s"$rowInd, $colInd")
        val threadCard = threadCards(rowInd)(colInd)
        threadCard.addMouseListener(new MouseListener {

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
}
