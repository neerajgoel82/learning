object Conversions {
  implicit def double2int(x: Double) = x.toInt
}

import Conversions._
val i:Int = 3.5


