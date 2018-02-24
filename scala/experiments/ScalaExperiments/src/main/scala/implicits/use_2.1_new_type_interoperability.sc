class Rational(n: Int, d: Int) {

  def this(n: Int) = this(n, 1)

  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)

  private val g = gcd(n, d)

  val numer: Int = n / g

  val denom: Int = d / g

  def +(that: Rational): Rational =
    new Rational(numer * that.denom + that.numer * denom,
      denom * that.denom)

  def -(that: Rational): Rational =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)

  def *(that: Rational): Rational =
    new Rational(numer * that.numer, denom * that.denom)

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  override def toString() = numer+"/"+denom

  def +(that: Int): Rational = this + new Rational(that)
  def -(that: Int): Rational = this - new Rational(that)
  def *(that: Int): Rational = this * new Rational(that)
  def /(that: Int): Rational = this / new Rational(that)
}

val oneHalf = new Rational(1, 2)
oneHalf + 1
oneHalf * 2

implicit def intToRational(x: Int) = new Rational(x, 1)

1 + oneHalf

/*
What happens behind the scene here is that
- Scala compiler first tries to typecheck the expression 1 + oneHalf as it is.
This fails because Int has several ‘+’ methods, but none that takes a Rational argument.

- Next, the compiler searches an implicit conversion from Int to another type that has a ‘+’
method which can be applied to a Rational. It finds our conversion and applies it, yielding
intToRational(1) + oneHalf
*/