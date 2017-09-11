class Time(val hours:Double, val minutes:Double = 0) {
  if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59) throw new IllegalArgumentException

  def before(other:Time): Boolean = {
    if((hours < other.hours) || (hours == other.hours && minutes < other.minutes)) true else false
  }

  override def toString: String = f"${hours}:${minutes}"
}

var t1 = new Time(0,19)
var t2 = new Time(0,16)
t1.before(t2)
var t3 = new Time(0)
t3.before(t2)


//var t3 = new Time(-1,0) //this will throw exception






