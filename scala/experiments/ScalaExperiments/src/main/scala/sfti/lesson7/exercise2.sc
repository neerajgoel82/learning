abstract class Item
case class Article(description:String, price:Double) extends Item
case class Bundle(description:String, discount:Double, items:Item*) extends Item
case class Whiskey(name:String, price:Double) extends Item

val book1 = Article("Scala For The Impatient", 30)
val bottle1 = Whiskey("Chivas Regal", 40)
val gift = Bundle("Christmas Pack", 10, book1, bottle1)

def price(item:Item): Double = {
  item match {
    case Article(_, price) => price
    case Whiskey(_, price) => price
    case Bundle(_,discount,items @ _*) => items.map( item => item match {
      case Bundle(_,_,_*) => price(item)
      case _ => price(item) * ( 1 - discount / 100)
    }).sum
  }
}

price(book1)
price(bottle1)
price(gift)

val gift2 = Bundle("Bumper Pack", 20, book1,bottle1,gift)
price(gift2)

