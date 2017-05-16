def swap(p:(Int, Int)) = {
  p match {case (first,second) => (second,first)}
}

swap(10,12)

def swapFirstTwo(arr:Array[Int]) = {
  arr match {
    case Array(first, second, rest @ _*) => Array(second,first) ++ rest
    case _ => arr
  }
}

swapFirstTwo(Array[Int](2,3,4,5,6,7))
swapFirstTwo(Array[Int](2,3))
swapFirstTwo(Array[Int](2))