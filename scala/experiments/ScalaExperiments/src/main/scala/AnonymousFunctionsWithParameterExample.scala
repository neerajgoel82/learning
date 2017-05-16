/**
  * Created by negoel on 4/18/2017.
  */

/**
  * Created by negoel on 4/18/2017.
  */

object TimerAnonymousWithCounter {
  def oncePerSecond(callback: (Int) => Unit) {
    var i : Int = 0 ;
    while (true) { callback(i); i+=1; Thread sleep 1000 }
  }
  def main(args: Array[String]) {
    oncePerSecond((counter) => {
      println("time flies like an arrow..." + counter)
    }
    )
  }
}

