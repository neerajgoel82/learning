val words = Array("Mary","had","a","little","lamb", "its", "fleece", "was", "white")
words.groupBy(_.substring(0,1))
words.groupBy(_.length)
