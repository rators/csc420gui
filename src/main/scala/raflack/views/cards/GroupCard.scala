package raflack.views.cards

import javax.swing.JLabel
import flagapp.conversions.SwingImpl._
import net.miginfocom.swing.MigLayout

/**
  * Created by rtorres on 10/22/16.
  */
class GroupCard(title: String, description: String, count: Int) extends RoundedPane(new MigLayout()) {
  val name = title
  val titleLabel = new JLabel(s"<html><h3><b>$title</b></h3></html>")
  val descriptionLabel = new JLabel(s"<html><h4>$description</h4><html>")
  val threads = new JLabel(s"<html><h5>Threads: $count</h5></html>")
  this += titleLabel -> "cell 0 0"
  this += descriptionLabel -> "cell 0 1"
  this += threads -> "cell 0 2"
}

object GroupCard {
  def apply(title: String, poster: String, count: Int) = new GroupCard(title, poster, count)
}