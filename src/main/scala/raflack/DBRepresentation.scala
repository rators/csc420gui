package raflack
import spray.json.{DefaultJsonProtocol, JsArray, JsNumber, JsString, JsValue, RootJsonFormat}
import spray.json._
import MyJsonProtocol._
import com.mongodb.casbah.MongoDB
import com.mongodb.casbah.commons.MongoDBObject

import scala.collection.mutable.ArrayBuffer

case class Comment(name: String, text: String)

case class RThread(name: String, comments: ArrayBuffer[Comment])

case class Group(_id: Int, title: String, description: String, threads: ArrayBuffer[RThread]){
  def toMongo = MongoDBObject(this.toJson.toString)
}
