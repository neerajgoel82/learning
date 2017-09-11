def isVowel(c:Char) = {
  if( c == 'a' || c == 'e' || c == 'i' || c== 'o' || c=='u') true
  else false
}

isVowel('f')
isVowel('a')

def isVowelUsingContains(c:Char) = "aeiouAEIOU".contains(c)

isVowelUsingContains('f')
isVowelUsingContains('a')
isVowelUsingContains('E')

def vowels(s:String) = {
  var output : String = ""
  for (letter<-s if (isVowel(letter))) output += letter
  output
}

vowels("neeraj")

def vowels2(s:String) = for (letter<-s if (isVowel(letter))) yield letter

vowels2("neeraj")

def vowels3(s:String):String = {
  if (s.length == 0) ""
  else if (isVowel(s.head)) s.head + vowels3(s.tail) else vowels3(s.tail)
}

vowels3("neeraj")

def vowels4(s:String):String = {
  var i = 0
  var output = ""
  while (i < s.length) {
    if(isVowel(s(i))) output += s(i)
    i+=1
  }
  output
}

vowels4("neeraj")

def isVowel2(c:Char, vowelChars:String = "aeiou", ignoreCase:Boolean = true) = {
  if(ignoreCase) vowelChars.toLowerCase.contains(c.toLower)
  else vowelChars.contains(c)
}

def vowels5(s:String, vowelChars:String = "aeiou", ignoreCase:Boolean = true) = {
  for (letter<-s if(isVowel2(letter, vowelChars, ignoreCase))) yield letter
}

vowels5("neeraj")
vowels5("neEraj")
vowels5("neEraj", ignoreCase = false)
vowels5("neEraj", vowelChars = "naeiou")
