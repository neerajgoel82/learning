import java.util.Random

trait Generator[+T] {
  self =>

  def generate: T

  def map[U](f: (T => U)): Generator[U] = new Generator[U] {
    def generate = f(self.generate)
  }

  def flatMap[U](f: (T => Generator[U])): Generator[U] = new Generator[U] {
    def generate = f(self.generate).generate
  }
}

val integers = new Generator[Int] {
  val rand = new Random
  def generate = rand.nextInt()
}

val booleans = for (x <- integers) yield x > 0
val booleans2 = integers.map(_ > 0 )

booleans.generate
booleans.generate
booleans2.generate


val pairs = for (x <- integers; y <- integers) yield (x,y)
val pairs2 = integers.flatMap(x => integers.map(y => (x,y)))

pairs.generate
pairs2.generate

def single[T](x: T) = new Generator[T] {
  def generate = x
}
val fiveGenerator = single(5)
fiveGenerator.generate
fiveGenerator.generate

def choose(low: Int, high: Int) : Generator[Int] = {
  for(x <- integers) yield low + math.abs(x % (high - low))
}
choose(4,10).generate

def oneOf[T](xs: T*): Generator[T] = {
  for(idx <- choose(0,xs.length)) yield xs(idx)
}

oneOf(1,2,3).generate

def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if (isEmpty) emptyList else nonEmptyList
} yield list

def emptyList = single(Nil)

def nonEmptyList = for {
  head <- integers
  tail <- lists
} yield head :: tail


print(lists.generate)

trait Tree
case class Inner(left: Tree, right: Tree) extends Tree
case class Leaf(value:Int) extends Tree

def leaves : Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)

def inners : Generator[Inner] = for {
  left <- trees
  right <- trees
} yield Inner(left, right)

def trees : Generator[Tree] =
  for {
    isLeaf <- booleans
    node <- if (isLeaf) leaves else inners
  } yield node

def printTree (root:Tree): Unit = {
  root match {
    case Inner(left, right) =>
      printTree(left)
      printTree(right)
    case Leaf(value) => print(f"[${value}]")
  }
}

printTree(trees.generate)


/*
def generatePowers(max: Int) = (Iterator.iterate(1)(_ * 2) takeWhile (_ < ((max+1) * 2))).toList
assert(generatePowers(63).last == 64)
*/

