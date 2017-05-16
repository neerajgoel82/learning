println("Hello, world!")

//val is immutable
val i = 10
//i = 20  //this is a compile time error

//var is mutable
var answer : Double = 2 + 3 * 6
answer = 43

//it is statically typed and specifying a type is optional
var intVar = 5
//intVar = "error" //this is a compile time error

//everything is an object. There are no primitive types
1.to(10) //gives a range object and by callling a function "to" on 1

//java.lang.String exists with additional functions like intersect. Enriched class is called StringOps
"hello".intersect("world")

//similar thing is with BigInt. Has additional function like '*' operator
val x : BigInt = 123456789
x*x*x*x

// two ways of calling a method with single param (infix notation, method notation)
1.to(10)
1 to 10

1.+(10)
1 + 10

//++, -- is not available use += instead
var y = 1
//y++ //this is a compile time error
y+=1

//scala has both functions and methods
import scala.math._
sqrt(2) //function .. equivalent to static function of Java

BigInt.probablePrime(100, scala.util.Random) //A method and is a static method from Java

//Methods without parameters ususally dont use ()
"Hello".distinct

//Rule of thumb: ()only required for mutators
"Hello".length

//Common to use (arg) instead of object.apply(4)
"Hello"(4) //is equivalent to
"Hello".apply(4)

//if expression has a value. It is equivalent to (x>0)?1:-1
if (x > 0 ) 1 else -1

//type of value of if expression is data type of return values. If they are different, then it will be
//base type of both the output values like in this example its "Any" since if is returning Int and
// else is returning String
if (x > 0 ) 1 else "miuns one"


//Unit is equivalent to void which is represented as ()
if (x < 0) "something"
val tmp = () //notice the type of tmp






