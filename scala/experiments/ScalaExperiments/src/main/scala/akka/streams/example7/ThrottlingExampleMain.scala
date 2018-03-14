package akka.streams.example7

import java.nio.file.Paths

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent._
import scala.concurrent.duration.DurationInt

object ThrottlingExampleMain extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  def lineSink(filename: String): Sink[String, Future[IOResult]] =
    Flow[String]
      .map(s => ByteString(s + "\n"))
      .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)

  val source: Source[Int, NotUsed] = Source(1 to 11)

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

  val done: Future[Done] = factorials
    .zipWith(Source(0 to 10))((num, idx) => s"$idx! = $num")
    .throttle(1, 2.seconds, 5, ThrottleMode.shaping)
    .runForeach(println)

  done.onComplete(_ => system.terminate())
}
