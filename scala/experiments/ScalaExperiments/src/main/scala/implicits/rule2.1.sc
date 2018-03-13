class Bar {
  def printSomehing: Unit = {
    print("Inside Bar")
  }
}

object Foo {
  implicit def foo2bar(x: Foo) = new Bar
}

class Foo {
  def printSomehing: Unit = {
    print("Inside Foo")
  }
}

val bar: Bar = new Foo
bar.printSomehing