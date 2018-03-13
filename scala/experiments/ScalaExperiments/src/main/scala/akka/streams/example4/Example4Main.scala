package akka.streams.example4
import akka.stream._
import akka.stream.scaladsl._
import akka.{ NotUsed, Done }
import akka.actor.ActorSystem
import akka.util.ByteString
import scala.concurrent._
import scala.concurrent.duration._
import java.nio.file.Paths

object Example4Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val source: Source[Int, NotUsed] = Source(1 to 100)

  //val done: Future[Done] = source.runForeach(i => println(i))(materializer)
  //done.onComplete(_ => system.terminate())

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

  val result: Future[IOResult] =
    factorials
      .map(num => ByteString(s"$num\n"))
      .runWith(FileIO.toPath(Paths.get("factorials.txt")))

  result.onComplete(iOResultOption => {
    print(s"Written : ${iOResultOption.get.count}")
    system.terminate()
  })
}
