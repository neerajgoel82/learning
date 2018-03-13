package akka.streams.example6

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent._

object ReusableSinkMain extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  def lineSink(filename: String): Sink[String, Future[IOResult]] =
    Flow[String]
      .map(s => ByteString(s + "\n"))
      .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)

  val source: Source[Int, NotUsed] = Source(1 to 100)

  //val done: Future[Done] = source.runForeach(i => println(i))(materializer)
  //done.onComplete(_ => system.terminate())

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)


  val result: Future[IOResult] =
    factorials.map(_.toString).runWith(lineSink("factorial2.txt"))

  result.onComplete(iOResultOption => {
    print(s"Written : ${iOResultOption.get.count}")
    system.terminate()
  })
}
