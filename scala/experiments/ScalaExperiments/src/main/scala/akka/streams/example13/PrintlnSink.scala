package akka.streams.example13

import akka.stream.{Attributes, Inlet, SinkShape}
import akka.stream.stage.{AbstractInHandler, GraphStage, GraphStageLogic}

class PrintlnSink extends GraphStage[SinkShape[String]]{
  val inlet: Inlet[String] = Inlet.create[String]("PrintlnSink.in")
  val inletShape = SinkShape.of(inlet)
  override def shape: SinkShape[String] = inletShape
  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = {
    new GraphStageLogic(shape) {
      var count = 0
      setHandler(inlet, new AbstractInHandler() {
        override def onPush(): Unit = {
          val element = grab(inlet)
          println(s"Element[${count}] : ${element}")
          count += 1
          pull(inlet)
        }
      })
      override def preStart(): Unit = { // initiate the flow of data by issuing a first pull on materialization:
        pull(inlet)
      }
    }
  }

}
