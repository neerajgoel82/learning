/**
  * Created by negoel on 4/25/2017.
  */
(1 to 10).reduceLeft(_ * _)

def fac(n:Int) = (1 to n).reduceLeft(_ * _)
fac(5)

def powN(n:Int) = Array.fill(n){2}.reduceLeft(_*_)
powN(4)

def powN2(n:Int) = Array.ofDim[Int](n).map(_=>2).reduce(_*_)
powN2(5)

def powN3(a:Int, b:Int) = (1 to b).map(_=>a).reduce(_*_)
powN3(2,3)

def concat(values : Seq[String], separator: String) = values.reduceLeft(_ + separator + _)
concat(Array("This", "is", "cool"), " : ")

