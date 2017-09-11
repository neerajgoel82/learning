//declaring a new class. Note that its immutable as the instance fields are val and move function
//returns a new instance
//Immutable classes are preferred as they are easy to share with anyone. Especially important in
//concurrent programs
class Point(val x: Double, val y: Double) { //x and y are immutable //use var instead of val for mutable instance variables
  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}

val point1 = new Point(2,3)
point1.distanceFromOrigin
point1.x //we can access the instance variable
point1.move(1,1).distanceFromOrigin
print(point1)

class Point2(var x: Double, var y: Double) { //x and y are immutable //use var instead of val for mutable instance variables
  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}

val point2 = new Point2(2,3)
point2.x = 4 //now this is allowed as x is var and not val
point2.distanceFromOrigin

//we can also have private instance variables
class BankAccount {
  private var balance = 0.0
  def getBalace = balance
}

var account1 = new BankAccount
account1.getBalace
//account1.balance //this is a compile time error as balance is private


//class Point2(var x: Double, var y: Double)
//By using the parameters with class name, we get the following
//- instance variables/values
//- primary constructor that initializes these instance variables
//- getters
//- setters if they are vars

//auxilliary constuctor can be defined as following
class Point3(var x: Double, var y: Double) { //x and y are immutable //use var instead of val for mutable instance variables
  def this() { this(0,0)}
  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}

val point3 = new Point3()
point3.x

//auxilliary consructors are not common because we can have default values as well
class Point4(var x: Double = 0 , var y: Double = 0) { //x and y are immutable //use var instead of val for mutable instance variables
  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}
val point4 = new Point4()
point4.x


//we can have any arbitrary code in the class body and that becomes part of the constructor
class Point5(var x: Double = 0 , var y: Double = 0) { //x and y are immutable //use var instead of val for mutable instance variables
  println(f"Constructed point: ${x} ,${y}")

  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}

val point5 = new Point5(4,5)
point5.x


//In our earlier examples, p.x and p.distanceFromOrigin are accessed in a similar fashion
//so we can't know whether is a parameterless method or instance variable. This is called
//"Uniform Access"

//Use the operator notation for binary methods
// x op y is equivalent to x.op(y)
class Point6(var x: Double = 0 , var y: Double = 0) {
  def *(factor: Double) = new Point6(x*factor, y*factor)
  def move(dx:Double, dy:Double) = new Point(x+dx, y+dy)
  def distanceFromOrigin = math.sqrt(x*x+y*y) //no () for parameter less accessor method
  override def toString = f"${x},${y}" //override keyword is compulsory for overriding a base class method
}

val point6 = new Point6(4,5)
val point7 = point6 * 2


//if operator ends with :, it is right associative or else it is left associative
1 :: 2 :: 3 :: Nil //this is equivalent to (1 :: ( 2 :: (3::Nil)))

//use object for singletons, static methods
object Accounts {
  private var lastNumber = 0
  def newAccountNumber() = { lastNumber += 1; lastNumber}
  //Use parenthesis () as it mutates state
}

Accounts.newAccountNumber()
Accounts.newAccountNumber()

//This is a companion object eliminating the need to call "new Point6(x,y)"
//Now we can call Point6(x,y) which internally call Point6.apply(x,y)
object Point6 {
  def apply(x:Double = 0 ,y:Double = 0) : Point6 = new Point6(x,y)
}

Point6(3,4) * 3


