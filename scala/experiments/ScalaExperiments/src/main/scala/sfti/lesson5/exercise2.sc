trait Logged {
  def log(msg:String) {}
}

trait ConsoleLogger extends  Logged{
  override def log(msg:String) = {println(msg)}
}

trait ShortLogger extends Logged {
  val maxLength = 15
  override def log(msg: String) = {
    if(msg.length <= maxLength) super.log(msg)
    else super.log(msg.substring(0,maxLength) + "...")
  }
}

trait TimestampLogger extends Logged {
  override def log(msg:String) = {
    super.log(new java.util.Date() + " " + msg )
  }
}

class BankAccount(var balance:Double) extends Logged {
  def withdraw(amount:Double) = {
    if(amount>balance) log("Insufficient funds")
    else balance = balance - amount
    balance
  }
}
val mgAccount = new BankAccount(1000) with ConsoleLogger with ShortLogger with TimestampLogger {
  override val maxLength = 15 //override the value in trait
}
mgAccount.withdraw(400)
mgAccount.withdraw(800)