import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

import spray.json._

import scala.util.{Failure, Success}

class GetExample {

}

case class MyObj(str:String, i:Int)

object Test extends App {
  import akka.actor.ActorSystem
  import akka.io.IO
  import akka.pattern.ask
  import akka.util.Timeout
  import spray.can.Http
  import spray.http._
  import HttpMethods._

  import scala.concurrent.Future
  import scala.concurrent.duration._

  implicit val system: ActorSystem = ActorSystem()
  implicit val timeout: Timeout = Timeout(15.seconds)
  import system.dispatcher // implicit execution context

  val response: Future[HttpResponse] =
    (IO(Http) ? HttpRequest(GET, Uri("http://www.geognos.com/api/en/countries/info/all.json"))).mapTo[HttpResponse]

  val response3: Future[HttpResponse] =
    (IO(Http) ? HttpRequest(GET, Uri("http://www.geognos.com/api/en/countries/flag/GR.png"))).mapTo[HttpResponse]

  response3.onSuccess {
    case e =>
      val image = ImageIO.read(new ByteArrayInputStream(e.entity.data.toByteArray))
      println()
  }

  response.onComplete {
    case Success(v) =>
      val countryData = v.entity.data.asString.parseJson.asJsObject.getFields("Results").head.asJsObject.fields
      println(countryData.keys)
    case Failure(_) => throw new RuntimeException("Fuck!")
  }
}