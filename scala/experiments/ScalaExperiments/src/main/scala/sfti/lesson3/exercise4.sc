val (upper,lower) = "New York".partition(_.isUpper)
upper
lower

val nums = Array (1,3,4,6,-1,-7,-8,6,-9)
val (neg,pos) = nums.partition(_ < 0)
pos
neg


val symbols = Array("<", "-",">")
var counts = Array(2,10,2)
var pairs = symbols.zip(counts)
pairs

for ((k,v) <- pairs) print(k * v)

