/**
  * Created by negoel on 4/18/2017.
  */

import java.util.{Date, Locale}
import java.text.DateFormat
import java.text.DateFormat._
object JavaInScalaExample {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
}