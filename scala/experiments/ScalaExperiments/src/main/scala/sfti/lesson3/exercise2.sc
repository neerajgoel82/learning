import java.util.Scanner

import scala.collection.mutable

def countWords(url:String) = {
  val in = new Scanner(new java.net.URL(url).openStream())
  val counts = mutable.Map[String,Int]()
  while (in.hasNext) {
    val word = in.next()
    counts(word) = counts.getOrElse(word,0) + 1
  }
  counts
}

val counts = countWords("http://horstmann.com/presentations/livelessons-scala-2016/alice30.txt")
counts("Alice")
counts("Rabbit")


def countWords2(url:String) = {
  val in = new Scanner(new java.net.URL(url).openStream())
  var counts = scala.collection.immutable.Map[String,Int]()
  while (in.hasNext) {
    val word = in.next()
    counts = counts + (word -> (counts.getOrElse(word,0) + 1))
  }
  counts
}

val counts2 = countWords("http://horstmann.com/presentations/livelessons-scala-2016/alice30.txt")
counts2("Alice")
counts2("Rabbit")