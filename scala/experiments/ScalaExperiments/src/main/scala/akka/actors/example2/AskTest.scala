package akka.actors.example2

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

case object AskNameMessage

class TestActor(service:MyService) extends Actor {
  def receive = {
    case AskNameMessage => // respond to the 'ask' request
      sender ! service.getMessage
    case _ => println("that was unexpected")
  }
}

class MyService(implicit val ec:ExecutionContext) {
  def getMessage = Future { "Neeraj" }
}


object AskTest extends App {
  implicit val ec = scala.concurrent.ExecutionContext.Implicits.global
  implicit val timeout = Timeout(5 second)
  val system = ActorSystem("AskTestSystem")

  val myService = new MyService ()


  val myActor = system.actorOf(Props(new TestActor(myService)), name = "myActor")

  val future2: Future[String] = ask(myActor, AskNameMessage).mapTo[Future[String]].flatMap(identity)
  future2.flatMap(message => {
    println(message)
    system.terminate
  })
}