class Time2(h:Int, m:Int = 0) {
  if(h < 0 || h > 23 || m < 0 || m > 59) throw new IllegalArgumentException
  private var minutesSinceMidnight = h * 60 + m

  def hours = minutesSinceMidnight / 60
  def minutes = minutesSinceMidnight % 60

  def minutes_=(newM:Int) = {
    if(newM < 0 || newM > 59) throw new IllegalArgumentException
    minutesSinceMidnight = h * 60 + newM
  }

  def before(other:Time2): Boolean = {
    if(minutesSinceMidnight < other.minutesSinceMidnight) true else false
  }

  override def toString: String = f"${hours}:${minutes}"
}

val t4 = new Time2(0,19)
var t5 = new Time2(0,16)
t4.before(t5)
t5
t5.minutes = 55
t5
t5.minutes = 550