package akka.streams.example2

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object RunForEachExample extends App {
  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()

  // Source
  val input = Source(1 to 100)

  input.runForeach(i ⇒ println(i))(flowMaterializer)
}
