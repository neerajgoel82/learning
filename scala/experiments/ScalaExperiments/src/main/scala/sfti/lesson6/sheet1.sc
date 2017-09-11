//A function is a first class citizen

import scala.math._

import scala.math._
val num = 3.14

//you can call the function
val fun = ceil _
fun(num)

//you can supply it to any other function
Array(2.5,4.3,5.6).map(fun)

//supplying anonymous functions
Array(2.5,4.3,5.6).map((x:Double) => 3 * x )
//or you can choose to name it
var triple = (x:Double) => 3 * x
Array(2.5,4.3,5.6).map(triple)

//higher order function ==> Functions consuming/producing a function
def valueAtOneQuarter(f:(Double)=>Double) = f(0.25)
valueAtOneQuarter(ceil)
valueAtOneQuarter(sqrt)
valueAtOneQuarter((x:Double) => 3*x)
valueAtOneQuarter(x => 3*x) //scala will infer type
valueAtOneQuarter(3*_)      //since there is only one param we can use _ as well

//function can produce other function
def mulBy(factor:Double) = (x:Double) => factor * x
mulBy(3)(4)
val quintuple = mulBy(5)
val product = quintuple(2)


//map function
(1 to 9).map( _ * 0.1)

//filter fulfils a predicate
(1 to 9).filter( _%2 == 0)

//reduceLeft => (((1*2)*3) *4)...
(1 to 9).reduceLeft(_ * _)

//reduceRight => (1*(2*(3*4)))...
(1 to 9).reduceRight(_ * _)

//map and filter are equivalent to for yield with a guard


//Closure = function + value of free variables
//They are implemented as objects with instance variables for captured parameters

//currying => turning a function which takes 2 arguments in a function that takes
//first argument and returns a function that takes second argument as input and returns the result
//curried version of multiply function
def mul(x:Double) = (y:Double) => x * y
mul(2)(3)

//scala syntactic sugar for currying
def scalaCurriedMul(x:Double)(y:Double) = x * y
scalaCurriedMul(2)(3)

//currying sometimes helps with type inference (check out https://www.safaribooksonline.com/library/view/scala-for-the/9780134510613/SCLA_06_04.html at the end)


//Control Abstractions => when we want to do something in new thread, on button click etc.
def runInThread(block:()=>Unit) = {
  new Thread() {
    override def run() {block()}
  }.start()
}

runInThread(()=>{println("Hello World : Run in a new thread")})

def runInThreadByName(block: =>Unit) = { //Please notice we've removed ()
  new Thread() {
    override def run() {block}
  }.start()
}

runInThreadByName {println("Call By Name in a new thread")}


