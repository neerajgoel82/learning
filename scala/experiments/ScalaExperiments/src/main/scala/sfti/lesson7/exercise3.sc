import java.util.NoSuchElementException

import scala.math

abstract class DoubleOption
case class SomeDouble(value:Double) extends DoubleOption
case object NoDouble extends DoubleOption

def inv(x: Double): DoubleOption = if (x==0) NoDouble else SomeDouble(1/x)
def foo(x: Double): DoubleOption = if (x <= 1) SomeDouble(math.sqrt(1-x)) else NoDouble
inv(2)
inv(0)

def compose(foo:(Double)=>DoubleOption, bar:(Double) => DoubleOption): (Double) => DoubleOption = {
  (x:Double) => {
    var barVal = bar(x)
    barVal match {
      case NoDouble => NoDouble
      case SomeDouble(b) => foo(b)
    }
  }
}

var h = compose(inv, foo)
h(1)
h(2)
h(0)

def isEmpty(x:DoubleOption): Boolean = {
  x match {
    case NoDouble => true
    case SomeDouble(_) => false
  }
}

def get(x:DoubleOption): Double = {
  x match {
    case NoDouble => throw new NoSuchElementException
    case SomeDouble(value) => value
  }
}

var composed = compose(inv, foo)
val first = h(1)
val second = h(2)
val third = h(0)

if(!isEmpty(first)) get(first)
if(!isEmpty(second)) get(second)
if(!isEmpty(third)) get(third)


abstract class DoubleOption2 {
  def isEmpty: Boolean
  def get: Double
}

class SomeDouble2(value:Double) extends DoubleOption2 {
  def isEmpty: Boolean = false
  def get: Double = value
}
object NoDouble2 extends DoubleOption2 {
  def isEmpty: Boolean = true
  def get: Double = throw new NoSuchElementException
}

val tmp1 = new SomeDouble2(1.0)
val tmp2 = NoDouble2

if (!tmp1.isEmpty) tmp1.get
if (!tmp2.isEmpty) tmp2.get


