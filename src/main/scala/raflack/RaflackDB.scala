package raflack

import raflack.MyJsonProtocol._
import spray.json._

import scala.collection.mutable.ArrayBuffer
import com.mongodb.casbah.Imports._

object RaflackDB extends App {
  val mongoClient = MongoClient("localhost", 27017)

  val db = mongoClient("raflack")
  println(s"The collections in the test database at initialization are: ${db.collectionNames}")

  val coll = db("raflack") // get a collection from the test database

  val comments = ArrayBuffer(
    Comment("paul", "trump sucks")
  )

  val threads = ArrayBuffer(
    Thread("Election 2016", comments)
  )

  val startingData = Group("General".hashCode, "General", threads)
  coll.insert(startingData.jsonString)

  val allDocs = coll.find()

  for(doc <- allDocs) println( doc )

//
//  val result = coll.findOne(MongoDBObject("_id" -> 3))
//  coll.insert(MongoDBObject(initData.toJson.toString))
//  val allDocsAfter = coll.find()
//  for(doc <- allDocs) println( doc )
//
//  mongoClient.close()
}