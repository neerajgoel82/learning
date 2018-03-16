package akka.streams.example13

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import akka.streams.example13.PrintlnSink

object PrintlnSinkExample extends App {
  implicit val system = ActorSystem("PrintlnSinkExample")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val source: Source[String, NotUsed] = Source("element1" :: "element2" :: "element3" :: Nil)
  val sink = new PrintlnSink
  val done = source.to(sink).run()
}
