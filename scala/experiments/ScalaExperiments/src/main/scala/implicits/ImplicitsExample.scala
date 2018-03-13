package implicits

object ImplicitsExample {
  implicit val favoriteNumber = 4
  def printSomething(implicit x: Int) = println(x)
  printSomething
}
