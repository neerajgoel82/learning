def maxList[T](nums: List[T])
              (implicit orderer: T=>Ordered[T]): T =
  nums match {
    case List() => throw new Error("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxList(rest)(orderer)
      if (orderer(x) > maxRest) x
      else maxRest
  }


maxList(List(1,5,10,3))

maxList(List(1.5, 5.2, 10.7, 3.14159))




def maxList2[T](nums: List[T])
  (implicit orderer: T=>Ordered[T]): T =
  nums match {
    case Nil => throw new Error("empty list!")
    case x :: Nil => x
    case x :: rest =>
      val maxRest = maxList2(rest) // (orderer) is redundant
      if (x > maxRest) x // orderer(x) is redundant
      else maxRest
  }

maxList2(List(1.5, 5.2, 10.7, 3.14159))

//View Bounds
//Mentally, you can think of this code as saying, “I can use any T, so long as
//it can be treated as an Ordered[T].”
def maxList3[T <% Ordered[T]](nums: List[T]): T =
  nums match {
    case Nil => throw new Error("empty list!")
    case x :: Nil => x
    case x :: rest =>
      val maxRest = maxList3(rest) // (orderer) is redundant
      if (x > maxRest) x // orderer(x) is redundant
      else maxRest
  }

maxList3(List(1.5, 5.2, 10.7, 3.14159))