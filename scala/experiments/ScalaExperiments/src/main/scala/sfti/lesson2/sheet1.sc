import scala.math.sqrt

//value of the block is the value of the last expression
val distance = {
  val x0 = 0
  val y0 = 0
  val x1 = 1
  val y1 = 1
  val dx = x1 - x0
  val dy = y1 - y0
  sqrt(dx*dx + dy*dy)
}

//is the last statement of the block is assignment then the block value is () or Unit.
var unit = 0.0
val distanceUnit = {
  val x0 = 0
  val y0 = 0
  val x1 = 1
  val y1 = 1
  val dx = x1 - x0
  val dy = y1 - y0
  unit = sqrt(dx*dx + dy*dy)
}

//No three part for loop
//for(loop = 0; loop < 10 ; loop+=1 ) {} //This will give compile time error
for (loop <- 1 to 10) println(loop)
for (loop <- "Hello") println(loop)

//multiple "generators"
for (i <- 1 to 3; j <- 1 to 3 ) println(10 *i + j)

//Guards
for (i <- 1 to 3; j <- 1 to 3 if i != j) println(10 *i + j)

//Collecting results : Using yield you get a value/collection from a for loop
for (i <- 1 to 10) yield i % 3

//functions ... return type is inferred
def abs(x: Double) = if (x > 0 ) x else -x

//return type is not inferred for recursive functions
//def fac(n:Int) = if ( n <= 0) 1 else n * fac(n-1) // This is a compile time error
def fac(n:Int):Int = if ( n <= 0) 1 else n * fac(n-1)

//when we don't put "=" in front of function name it does not return anything
//They are called procedures or Unit functions
def abs2(x: Double) {if (x > 0 ) x else -x}

//you can have named arguments as well
abs(x=5)

//Default arguments let you omit argument values
def decorate (str: String, left:String = "[", right: String = "]") = left + str + right
decorate("Hello")
decorate("Hello", ">>>[")
decorate("Hello", right = "]<<<")

//Variable number of arguments are specified using *
def sum(args:Int*) = {
  var result = 0
  for (arg <- args) result += arg
  result
}

sum(1,2)

//if you already have a seq you cannot call sum using that
//sum(1 to 5) // this is a compile time error

//You need to use _* which means sequence of something
sum(1 to 5 : _*)

//Needed in recursive calls
def recursiveSum(args:Int*):Int = {
  if(args.length == 0) 0
  else args.head + recursiveSum(args.tail: _*)
}

recursiveSum(1,2,3,4)




