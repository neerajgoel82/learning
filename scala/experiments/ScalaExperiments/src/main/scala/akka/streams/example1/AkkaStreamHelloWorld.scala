package akka.streams.example1

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object AkkaStreamHelloWorld extends App {
  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()

  // Source
  val input = Source(1 to 100)

  // Flow
  val normalize = Flow[Int].map(_ * 2)

  //val normalizeAgain =

  // Sink
  val output = Sink.foreach[Int](println)

  input.via(normalize).runWith(output).andThen {
    case _ =>
      actorSystem.terminate()
  }
}
