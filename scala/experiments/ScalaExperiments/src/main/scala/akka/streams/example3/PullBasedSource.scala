package akka.streams.example3

import akka.stream.stage.{GraphStage, GraphStageLogic, OutHandler}
import akka.stream.{Attributes, Outlet, SourceShape}

import scala.util.Random


class PullBasedSource() extends GraphStage[SourceShape[Int]]{
  val out: Outlet[Int] = Outlet("Int.out")

  // Define the shape of this stage, which is SourceShape with the port we defined above
  override val shape: SourceShape[Int] = SourceShape(out)

  // This is where the actual (possibly stateful) logic will live
  override def createLogic(inheritedAttributes: Attributes): GraphStageLogic = new GraphStageLogic(shape) {
    // All state MUST be inside the GraphStageLogic,
    // never inside the enclosing GraphStage.
    // This state is safe to access and modify from all the
    // callbacks that are provided by GraphStageLogic and the
    // registered handlers.

    var count = 0
    setHandler(out, new OutHandler {
      override def onPull(): Unit =  {
        push(out,Random.nextInt())
        count += 1
      }
    })
  }
}
