class MyRichString(string: String) {
  def +++(that:String) = {
    string.concat(that)
  }
}

implicit def stringToMyRichString(string: String) = new MyRichString(string)

"abc" +++ "xyz"