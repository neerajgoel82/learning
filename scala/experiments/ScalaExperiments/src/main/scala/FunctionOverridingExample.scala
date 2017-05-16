/**
  * Created by negoel on 4/18/2017.
  */
class ComplexWithToString(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
  override def toString() =
    "" + re + (if (im < 0) "" else "+") + im + "i"
}

object ComplexNumbersWithToString {
  def main(args : Array[String]){
    val c = new ComplexWithToString(1.2,3.4)
    println(c)
  }
}
