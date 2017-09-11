"Hello World"

println("Welcome to scala worksheets")
val f: String => String = { case "ping" => "pong" }

f("ping")
//f("neeraj")

val fp: PartialFunction[String, String] = {case "ping" => "pong"}
fp.isDefinedAt("ping")
fp.isDefinedAt("neeraj")