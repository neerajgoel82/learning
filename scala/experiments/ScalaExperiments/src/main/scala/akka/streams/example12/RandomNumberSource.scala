package akka.streams.example12

import akka.stream.{Attributes, Outlet, SourceShape}
import akka.stream.stage.{GraphStage, GraphStageLogic}
import akka.stream.stage.AbstractOutHandler

import scala.util.Random

class RandomNumberSource extends GraphStage[SourceShape[Int]] {
  val outlet = Outlet.create[Int]("RandomNumberSource.out")
  val outletShape = SourceShape.of(outlet)
  override def shape: SourceShape[Int] = outletShape
  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = {
     new GraphStageLogic(shape) {
       setHandler(outlet, new AbstractOutHandler {
         override def onPull(): Unit = {
           push(outlet,Random.nextInt())
         }
       })
    }
  }
}
