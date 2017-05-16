import scala.collection.mutable.ArrayBuffer

def findNegative (nums : ArrayBuffer[Int]) = {
  var firstNegFound : Boolean = false
  var output = new ArrayBuffer[Int]
  for (num <- nums) {
    if(num >= 0 || !firstNegFound) output += num
    if(num < 0 ) firstNegFound = true
  }
  output
}

findNegative(ArrayBuffer(-1,3,5,6 ,7,-2,5,6,-3,4,5))


def findNegative2(nums : ArrayBuffer[Int]) = {
  var negIndexes  = for (i <- (0 until nums.length) if(nums(i) < 0 )) yield i
  for (index <- negIndexes.drop(1).reverse) nums.remove(index)
  nums
}
findNegative2(ArrayBuffer(-1,3,5,6 ,7,-2,5,6,-3,4,5))

def findNegative3(nums : ArrayBuffer[Int]) = {
  var negIndexes  = (for (i <- (0 until nums.length) if(nums(i) < 0 )) yield i).drop(1)
  for (i <- 0 until nums.length if(!negIndexes.contains(i)) ) yield nums(i)
}
findNegative3(ArrayBuffer(-1,3,5,6 ,7,-2,5,6,-3,4,5))

