class Time(val hours:Int, val minutes:Int = 0) {
  if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59) throw new IllegalArgumentException

  def <(other:Time): Boolean = {
    if((hours < other.hours) || (hours == other.hours && minutes < other.minutes)) true else false
  }

  def -(other:Time): Int = {
    (hours - other.hours) * 60 + minutes - other.minutes
  }

  override def toString: String = f"${hours}:${minutes}"
}

object Time {
  def apply(hours:Int, minutes:Int): Time = new Time(hours,minutes)
}

var t1 = new Time(4,19)
var t2 = Time(5,15)
t1 < t2
var t3 = new Time(0)
t3 < t2
t1-t2
t2-t1
