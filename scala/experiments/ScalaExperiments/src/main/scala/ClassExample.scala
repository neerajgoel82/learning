/**
  * Created by negoel on 4/18/2017.
  */
class Complex(real: Double, imaginary: Double) {
  def re = real
  def im() = imaginary
}

object ComplexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im())
    println("real part: " + c.re)
  }
}