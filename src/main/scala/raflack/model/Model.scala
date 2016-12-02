package raflack.model

import java.awt.Component

import raflack.RThread
import raflack.sections.ViewPanel

import scala.collection._
import scala.collection.mutable.ArrayBuffer

/**
  *
  */
trait Model {
  val components = ArrayBuffer[ViewPanel]()

  def <~(c: ViewPanel): this.type = {
    components += c
    this
  }

  def stateChanged() = {
    components.foreach((c) => {
      c.update()
      c.revalidate()
      c.validate()
      c.repaint()
    })
  }

  def shift[T](b :  => T): T = {
    val result = b
    stateChanged()
    result
  }
}

trait ListModel[T] extends mutable.Buffer[T] with Model {
  val buffer: mutable.Buffer[T] = mutable.Buffer[T]()

  override def apply(n: Int): T = {
    buffer.apply(n)
  }

  override def update(n: Int, newelem: T): Unit = shift {
    buffer.update(n, newelem)
  }

  override def length: Int = buffer.length

  override def +=(elem: T): this.type = shift {
    buffer += elem
    this
  }

  override def clear(): Unit = shift {
    buffer.clear()
  }

  override def +=:(elem: T): this.type = shift {
    buffer prepend elem
    this
  }

  override def insertAll(n: Int, elems: Traversable[T]): Unit = shift {
    buffer insertAll(n, elems)
  }

  override def remove(n: Int): T = shift {
    val r = buffer.remove(n)
    r
  }

  override def iterator: Iterator[T] = buffer.iterator
}
