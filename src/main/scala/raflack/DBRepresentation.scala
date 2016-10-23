package raflack
import spray.json.{DefaultJsonProtocol, JsArray, JsNumber, JsString, JsValue, RootJsonFormat}
import spray.json._ // if you don't supply your own Protocol (see below)

import scala.collection.mutable.ArrayBuffer

case class Comment(name: String, text: String)

case class Thread(name: String, comments: ArrayBuffer[Comment])

case class Group(_id: Int, title: String, threads: ArrayBuffer[Thread]){
  def jsonString = this.toJson.toString
}
