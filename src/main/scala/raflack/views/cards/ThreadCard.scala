package raflack.views.cards

import javax.swing.JLabel

import net.miginfocom.swing.MigLayout
import hw2.SwingImpl._
/**
  * A thread card.
  */
class ThreadCard(title: String, poster: String, count: Int) extends RoundedPane(new MigLayout()) {
  val titleLabel = new JLabel(s"<html><h3><b>$title</b></h3></html>")
  val descriptionLabel = new JLabel(s"<html><h4>Posted by: $poster</h4><html>")
  val replies = new JLabel(s"<html><h5>Replies: $count</h5></html>")
  this += titleLabel -> "cell 0 0"
  this += descriptionLabel -> "cell 0 1"
  this += replies -> "cell 0 2"
}

object ThreadCard {
  def apply(title: String, poster: String, count: Int) = new ThreadCard(title, poster, count)

}