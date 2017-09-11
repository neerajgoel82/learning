//Match is a deluxe version of Java switch
//Match is an expression like if and also does not require break
var ch = '5'
var digit:Int = 0
var sign:Int = 1
//var ch2:Char = '6'

  ch match {
  case '+' => sign = 1
  case '-' => sign = -1
  case _ if Character.isDigit(ch) => digit = Character.digit(ch,10) //Guards are also allowed
  case _ => sign = 0
}

//ch2
f"${sign}:${digit}"


//we can match for types as well. Preferred over isInstanceOf
//you also write catch block using pattern matching
val obj :Any = "34"
try {
  obj match {
    case x: Int => x
    case s: String => Integer.parseInt(s)
    case _: BigInt => Int.MaxValue
    case _ => 0
  }
}
catch {
  case e: NumberFormatException => println("Caught Number Format Exception:" + e)
  case e: Exception => println("Caught:" + e)
}


//can be used to extract elements of pair
var pair = (5,0)
pair match {
  case (0,y) => f"{0},${y}"
  case (x,0) => f"${x},{0}"
  case (x,y) => f"{0},${y}"
}

//in arrays also we can do the pattern matching
var arr = Array[Int](0,3,2,6,7)
arr match {
  case Array(0) => print("case 1") //matches with array containing one element that is 0
  case Array(x,y) => print("case 2") //matches with array containing two elements
  case Array(0, _*) => print("case 23") //matches with array starting from 0 and containing other elements
  case _ => print("Unknown case") //everything else
}


//Extractions can happen in variable assignments as well
val Array(first, second, rest @ _*) = arr //rest @ _* is used for tail end of the list
for (i<-rest) print(i + " ")

val (var1, var2) = pair
val (var4, var5) = "Hello World".partition(_.isUpper)

abstract class Amount
case class Dollar(value:Double) extends Amount
case class Currency(value:Double, unit:String) extends Amount
case object Nothing extends Amount

var amt:Amount = Currency(10, "EUR")
amt match {
  case Dollar(value)=> printf(f"Dollar ${value}")
  case Currency(value, unit) => printf(f"${unit} ${value}")
  case Nothing=> printf("Nothing")
  case _ => printf("Not an amount")
}


//what happens with case classes
// - each of the constructor parameters becomes a val
// - companion object gets an apply factory method
// - Methods toString, equals, hashCode, unapply, copy are generated
// - unapply makes extractors work

//Option[T] is a safe alternative to providing a value which can be of type T or null
//case Some[T] wraps the value
//object None represents null
//Map<K,V>.get returns Some(V) or None
val scores = Map("Alice" -> 1, "Bob" ->2)
scores.get("Alice") match {
  case Some(score) => printf(f"Score: ${score}")
  case None => printf("Entry absent")
}

//case classes can be used for recursive data structures
abstract class Expr
case class Val(value:Int) extends Expr
case class Sum(left:Expr, right:Expr) extends Expr
case class Product(left:Expr, right:Expr) extends Expr

def eval(e:Expr): Int = {
  e match {
    case Val(value) => value
    case Sum(left, right) => eval(left) + eval(right)
    case Product(left, right) => eval(left) * eval(right)
  }
}

eval(Product(Val(3), Sum(Val(1), Val(3))))

//polymorphism is appropriate for an open ended collection
//Case classes and pattern matching are best for a bounded collection
//Case is more concise with a case classes
//All cases are in one place
