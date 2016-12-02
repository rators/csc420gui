package raflack.model

import raflack.{Group, RThread}

import scala.collection.mutable

/**
  *
  */
class ThreadsModel() extends ListModel[RThread] {
  def this(it: Iterable[RThread]) = {
    this()
    it.foreach(buffer += _)
  }
}

class ThreadsLandingModel(private var _group: Option[Group], it: Iterable[RThread]) extends ThreadsModel() {

  override def +=(elem: RThread) = shift {
    buffer += elem
    _group match {
      case Some(g) => g.threads += elem
      case None => ()
    }
    this
  }

  def group: Option[Group] = _group

  def group_=(g: Group): Unit = _group = Some(g)
}