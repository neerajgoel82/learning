import java.awt.Rectangle

val rect = new Rectangle(5,10,20,30)
rect.translate(10,20)
rect


import java.awt.geom

class MyEllipse(x:Double, y:Double, w:Double, h:Double) extends geom.Ellipse2D.Double(x,y,w,h) {
  def translate(dx:Double = 0 , dy:Double = 0, dw:Double = 0 , dh:Double = 0): MyEllipse = {
    new MyEllipse(x + dx, y + dy, h+dh, w + dw)
  }

  override def toString: String = f"${x}:${y}:${w}:${h}"
}

val egg = new MyEllipse(5,10,20,30)
val egg2 = egg.translate(10,20)

trait RectangleLike {
  def setFrame(x:Double, y:Double, w:Double, h:Double)
  def getX : Double
  def getY : Double
  def getWidth : Double
  def getHeight : Double
  def translate(dx:Double, dy:Double) = setFrame(getX+dx, getY+dy,getWidth,getHeight)
  override def toString: String = f"${getX}:${getY}:${getWidth}:${getHeight}"
}


val egg3 = new geom.Ellipse2D.Double(5,10,20,30) with RectangleLike
egg3.translate(10,20)
egg3


