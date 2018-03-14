package akka.streams.example12

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.{Done, NotUsed}
import akka.stream.scaladsl.Source
import akka.streams.example10.BufferingExample.{Author, Hashtag, Tweet}
import akka.stream.scaladsl._

import scala.concurrent.Future

object GraphStageExample extends App{
  implicit val system = ActorSystem("GraphStageExample")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val source: Source[Int, NotUsed] = Source.fromGraph(new RandomNumberSource)
  val done: Future[Done] = source.take(10).buffer(2, OverflowStrategy.dropHead).runWith(Sink.foreach(println))
  done.onComplete(_=>system.terminate())
}
