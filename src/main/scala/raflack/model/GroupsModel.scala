package raflack.model

import raflack.Group

/**
  * Created by rtorres on 12/2/16.
  */
/**
  *
  */
class GroupsModel() extends ListModel[Group] {
  def this(it: Iterable[Group]) = {
    this()
    it.foreach(buffer += _)
  }
}