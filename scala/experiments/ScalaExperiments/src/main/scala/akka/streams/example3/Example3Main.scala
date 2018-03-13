package akka.streams.example3

import akka.Done
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl.{Flow, GraphDSL, Keep, RunnableGraph, Sink, Source}
import akka.streams.example1.AkkaStreamHelloWorld.{actorSystem, input}
import akka.stream.scaladsl._
import GraphDSL.Implicits._

import scala.concurrent.Future

object Example3Main extends App{
  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()

  val source = Source.fromGraph(new PullBasedSource)

  //val normalize = Flow[Int].map(_ * 2)

  // Sink
  val output = Sink.foreach[Int](println)

  lazy val graph = GraphDSL.create(output) { implicit builder =>out =>
    source.watchTermination()(Keep.none) ~> output
    ClosedShape
  }

  def run() : Future[Done] = {
    val runnableGraph = RunnableGraph.fromGraph(graph)
    runnableGraph.run()
  }

  //run
}
