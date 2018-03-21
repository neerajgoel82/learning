package akka.actors.example3

import akka.actor.{ActorSystem, FSM, Props, Stash}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class MyService(implicit val ec:ExecutionContext) {
  def getMessage = Future { "Neeraj" }
}

class UserStorageFSM(service:MyService) extends FSM[UserStorageFSM.State, UserStorageFSM.Data] with Stash{
  import UserStorageFSM._

  startWith(Disconnected, EmptyData)

  when(Disconnected) {
    case Event(Connect, _) =>
      println("UserStorage connected to DB")
      unstashAll()
      goto(Connected) using EmptyData replying(service.getMessage)

    case Event(msg, _) =>
      stash()
      stay using EmptyData
  }

  when(Connected) {
    case Event(Disconnect, _) =>
      println("UserStorage disconnected from DB")
      goto(Disconnected) using EmptyData

    case Event(Operation(op, user), _) =>
      println(s"UserStorage receive ${op} operation to do in user: ${user}")
      stay using EmptyData
  }

  initialize()

}


object UserStorageFSM {
  sealed trait State
  case object Connected extends State
  case object Disconnected extends State

  sealed trait Data
  case object EmptyData extends Data

  trait DBOperation
  object DBOperation{
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(op: DBOperation, user: User)

  case class User(username: String, email: String)
}


object FiniteStateMachine extends App {
  import UserStorageFSM._

  val system = ActorSystem("Hotswap-FSM")
  import system.dispatcher
  implicit val timeout = Timeout(5 second)

  val userStorage = system.actorOf(Props(new UserStorageFSM(new MyService())), "userStorage-fsm")

  val future2: Future[String] = ask(userStorage, Connect).mapTo[Future[String]].flatMap(identity)
  future2.map(message => {
    println(message)
  })

  userStorage ! Operation(DBOperation.Create, User("Admin", "admin@packt.com"))

  userStorage ! Disconnect

  Thread.sleep(100)

  system.terminate()

}