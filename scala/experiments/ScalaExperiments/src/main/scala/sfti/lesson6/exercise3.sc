val n = 5
var i = 1
var f = 1

def While ( condition:()=>Boolean, block:() => Unit):Unit = {
  if(condition()) {
    block()
    While(condition,block)
  }
}

While( () => i <= n, ()=> {f*=i; i+=1})
f

def While2 ( condition: =>Boolean, block: => Unit):Unit = {
  if(condition) {
    block
    While2(condition,block)
  }
}

f = 1
i = 1
While2(i <= n, {f*=i; i+=1})
f

def While3( condition: =>Boolean) (block: => Unit):Unit = {
  if(condition) {
    block
    While3(condition)(block)
  }
}

f = 1
i = 1
While3(i <= n) {f*=i; i+=1}
f