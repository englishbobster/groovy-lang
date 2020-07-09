//do like this
[1, 2, 3].each{entry -> println entry}

//but the closure is a firstclass citizen
def theList = [1,2,3]
def doPrint = {entry -> println(entry)}
theList.each doPrint

//Reach out of the closure and modify the outer scope
def totalClinks = 0
def partyPeople = 100
1.upto (partyPeople) { guestNr ->
  clinksWithGuest = guestNr - 1
  totalClinks += clinksWithGuest //modify outerscope
}
println("the total nr of clinks: $totalClinks ")
assert totalClinks == (partyPeople * (partyPeople -1))/2

//the default iterator 'it'
//these 2 closures are equivalent
def log1 = ''
(0..9).each { counter ->
  log1 += counter
}
assert log1 == '0123456789'

def log2 = ''
(0..9).each {
  log2 += it
}
assert log2 == '0123456789'

//3 methods of declaring closures
//1) closure as a parameter using {} notation
Map theMap = [a: 1, b: 2]
theMap.each { key, val ->
  theMap[key] = val * 2
}
assert theMap == [a: 2, b: 4]

//2) declaring a closure
Closure doubler = { key, val -> theMap[key] = val * 2 }
theMap.each(doubler)
assert theMap == [a: 4, b: 8]

//as a method reference using &
//here there is no clue to that we will use this later as a closure...
def doubleMethod(entry) {
  entry.value = entry.value * 2
}

//...until we do this...
toDouble = this.&doubleMethod
theMap.each(toDouble)
assert theMap == [a: 8, b: 16]

//So how do we use closures?
//2 ways here ... (notice we can set a default value for the arguments!)
def adder = { x = 4, y = 3 -> return x + y }
assert adder(4, 3) == 7
assert adder.call(4, 3) == 7
assert adder.call(4) == 7
assert adder.call() == 7

//methods that take closures
def benchmark(int repetitions, Closure work) {
  def start = System.nanoTime()
  repetitions.times {
    work(it)
  }
  def stop = System.nanoTime()
  return stop - start
}

def slow = benchmark(10000) { it -> (int) it / 2 }
def fast = benchmark(10000) { it -> it.intdiv(2) }
println("fast: $fast nanos")
println("slow: $slow nanos")

assert fast < slow


//we can curry (partial application) with closures...
def configurator = { format, filter, line ->
  filter(line) ? format(line) : null
}
def appender = { config, append, line ->
  def out = config(line)
  if (out) append(out)
}

def dateFormater    = { line -> "${new Date()}: $line" }
def debugFilter     = { line -> line.contains('debug') }
def consoleAppender = { line -> print line }

//partial application...
def myConf = configurator.curry(dateFormater, debugFilter) //leaving only line to be applied at some later time
def myLog = appender.curry(myConf, consoleAppender) //again leaving only line to be applied at some later time

myLog('debug message: print this out')
myLog("hope this isn't printed")

