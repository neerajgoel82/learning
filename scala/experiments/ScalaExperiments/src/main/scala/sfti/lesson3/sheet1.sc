//Use square bracket for type
val nums = new Array[Int](10)

//Array initialization
val a = Array("Hello", "World")

//Use parantheses to access an element at an index
a(0)
a(0) = "GoodBye"
a

//array traversal
for(element <- a) println(element)

//traversing the array using index
for( i  <-  0 until a.length) println(a(i))

//ArrayBuffer is like ArrayList which is for variable length array
import scala.collection.mutable.ArrayBuffer
val b = new ArrayBuffer[Int]
b += 5 //add single element
b += (1, 3, 7) //add multiple elements
b ++= Array(4, 2, 8) //add a collection
b.insert(0,0) //add an element at a particular index
b
b.remove(0) //removes element at index 0
b
b.trimEnd(2) //removes last 2 elements
b

//toArray and toBuffer are used to convert between array and arraybuffer
val c = b.toArray

//transform each element and store in another array
val d = for(element <-b) yield element * 2

//to sort the array. This returns a new array and original is unchanged
b.sorted
b

//to find a max
b.max

//to reverese the array
b.reverse

b.mkString("|")


//Map creation. It is immutable by default
val scores = Map("Alice" -> 6, "Bob" -> 4, "Neeraj" -> 1)
scores("Bob")
//scores("Bob") = 7 //This is a compile time error
scores.getOrElse("Fred", 0) //if element is not present it does not throw an exception

val mScores = scala.collection.mutable.Map("Alice" -> 6)
mScores += ("Bob" -> 20)
mScores

//recommended way is to use immutable maps as internally they share the common elements
// Also helps with parallel programming
for((k,v) <- mScores) println(k,v)
for((k,v) <- mScores) yield (v,k)

mScores.keySet //set of keys
mScores.values //set of values

//Tuples collect values of different types
val t = (1,3.14,"Foo")
t._2

//pattern matching can be used to access parts of tuple
val (_,score,name) = t

var tmp = scala.collection.immutable.Map[String, Int]()
tmp = tmp + ("abc" -> 1)
tmp = tmp + ("abc" -> 2) //this overwrites the value of "abc"










