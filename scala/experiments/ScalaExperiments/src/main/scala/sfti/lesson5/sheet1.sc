import java.awt.{Color,Font} //import multiple classes
import scala.collection.immutable.{HashMap => ScalaHashMap} //alias ... Now you can use JavaHashMap in your program
import java.util.{HashMap => _, _ } // imports everything from java.util except HashMap

var test = new ScalaHashMap[String,Int]()
test = test + ("abc" -> 1)
test

def localImport = {
  //imports can be anywhere, helps in reducing the scope of import
  import scala.collection.immutable.HashMap
  var test = new HashMap[String,Int]()
  test = test + ("abc" -> 1)
  test
}

localImport


class Parent {

}

//inheritance
class Child extends Parent {

}

val emp1 = new Child()

//check for type in hierarchy
emp1.isInstanceOf[Parent]
emp1.isInstanceOf[Child]

//typecasting .. equivalent to (Parent) emp1
val tmpEmp = emp1.asInstanceOf[Parent]

//checks the exact class type
emp1.getClass == classOf[Parent]
emp1.getClass == classOf[Child]
tmpEmp.getClass == classOf[Child]


class Person(name:String) {
}

//super class constructor
class Employee(name:String, salary:Int) extends Person(name) {
}

//Traits are equivalent to java interfaces. There are no interfaces in scala
class Employee2(name:String, salary:Int) extends Person(name) with Cloneable with Serializable {
}

//Traits can have concrete methods
//Traits can have abstract fields. A concrete implementing class must supply them
//Traits can have concrete fields
//Traits cannot have primary constructors //technically this is the only difference between class and trait in scala

trait Logged {
  def log(msg:String) {}
}

trait ConsoleLogger extends  Logged{
  override def log(msg:String) = {println(msg)}
}

class BankAccount(var balance:Double) extends Logged {
  def withdraw(amount:Double) = {
    if(amount>balance) log("Insufficient funds")
    else balance = balance - amount
    balance
  }
}

val ngAccount = new BankAccount(1000) with ConsoleLogger //mixin
ngAccount.withdraw(400)
ngAccount.withdraw(800)

trait ShortLogger extends Logged {
  val maxLength = 15
  override def log(msg: String) = {
    if(msg.length <= maxLength) super.log(msg)
    else super.log(msg.substring(0,maxLength) + "...")
  }
}

trait TimestampLogger extends Logged {
  override def log(msg:String) = {
    super.log(new java.util.Date() + " " + msg )
  }
}

val mgAccount = new BankAccount(1000) with ConsoleLogger with TimestampLogger with ShortLogger {
  override val maxLength = 50 //override the value in trait
}
mgAccount.withdraw(400)
mgAccount.withdraw(800)
