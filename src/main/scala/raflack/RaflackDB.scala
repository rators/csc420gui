package raflack

import com.mongodb.casbah.Imports._
import raflack.MyJsonProtocol._
import spray.json._

import scala.collection.mutable.ArrayBuffer

class RaflackDB {
  val mongoClient = MongoClient("localhost", 27017)
  val db = mongoClient("raflack")
  val coll = db("raflack") // get a collection from the test database

  def getRoot: List[Group] = {
    coll.find.map(_.toString.parseJson.convertTo[Group]).toList
  }

  def addGroup(name: String, description: String) = {
    val newGroup = Group(name.hashCode, name, description, ArrayBuffer.empty)
    coll.insert(newGroup.toMongo)
    newGroup
  }

  def addGroup(group: Group) = {
    coll.insert(group.toMongo)
    group
  }

//  val comments = ArrayBuffer(
//    Comment("paul", "trump sucks")
//  )
//
//  val threads = ArrayBuffer(
//    Thread("Election 2016", comments)
//  )
//
//  val startingData = Group("General".hashCode, "General", threads)
//  coll.insert(startingData.toMongo)
//
//  val allDocs = coll.find()
//
//  for(doc <- allDocs) println( doc )

//
//  val result = coll.findOne(MongoDBObject("_id" -> 3))
//  coll.insert(MongoDBObject(initData.toJson.toString))
//  val allDocsAfter = coll.find()
//  for(doc <- allDocs) println( doc )
//
//  mongoClient.close()
}

object RaflackDB {
  def apply() = new RaflackDB()
}