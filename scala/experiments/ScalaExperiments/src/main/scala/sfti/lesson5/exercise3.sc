import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}


//val bufferedReader = new BufferedReader(new InputStreamReader(new InputStream("C:\\code\\mywork\\scala\\.gitignore")))
trait Logged {
  def log(msg:String) {}
}

trait ConsoleLogger extends Logged{
  override def log(msg:String) = {println(msg)}
}

trait Buffered extends InputStream with Logged{
  private val BUFFERSIZE = 1024
  private val buffer = new Array[Byte](BUFFERSIZE)
  private var pos:Int = 0
  private var end:Int = 0 ;
  override def read() = {
    if(pos == end) {
      end = super.read(buffer,0,BUFFERSIZE)
      log("Buffer was empty")
      pos = 0
    }

    if(pos == end) -1
    else {
      pos += 1
      buffer(pos - 1)
    }
  }
}

val bufferedReader = new FileInputStream("C:\\code\\mywork\\scala\\.gitignore") with Buffered with ConsoleLogger

var byte = bufferedReader.read()
byte = bufferedReader.read()
