def alist = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
def blist = (0..9).toList()

assert alist == blist

//slice a list
//with a range
assert alist[2..5] == [2, 3, 4, 5]
//just pick some indeces
assert alist[1, 4, 5] == [1, 4, 5]

//replace a range
alist[0..2] = [4, 5, 6]
assert alist == [4, 5, 6, 3, 4, 5, 6, 7, 8, 9]

//delete a range
alist[0..2] = []
assert alist == [3, 4, 5, 6, 7, 8, 9]

//add elements? or replace "3" with a list and flatten
alist[0..0] = ['x', 'y', 'z']
assert alist == ['x', 'y', 'z', 4, 5, 6, 7, 8, 9]

//removing elements
alist.remove(2) //by index
assert alist == ['x', 'y', 4, 5, 6, 7, 8, 9]
alist.remove('x')
assert alist == ['y', 4, 5, 6, 7, 8, 9] //by object

//what about negative indexes
assert alist[-1] == 9
assert alist[-3..-1] == [7, 8, 9]
assert alist[-1..-3] == [9, 8, 7]

//dont go over the bounds in reverse though
try {
  def notPossible = [-15]
} catch (ArrayIndexOutOfBoundsException e) {
  assert true
}

//some operators
def xlist = []
xlist += 'a'
assert xlist == ['a']
// but how do we have a list of lists?
xlist += [['a']]
assert xlist == ['a', ['a']]
// mulitplication
println(xlist * 2)
// and a very nice leftshift to append
xlist << 'c' << 'd'
assert xlist == ['a', ['a'], 'c', 'd']

//control structures with lists
def ylist = [10, 20, 30, 40, 50]

assert ylist.isCase(10)
assert 20 in ylist

switch (30) {
  case ylist: assert true; break
  default: assert false
}

def expr = ''
for (i in ylist) {
  expr += i
}
assert expr == '1020304050'

//we can find elements in common with grep (intersection)
assert [100, 200, 30, 400, 500].grep(ylist) == [30]
//alternatively
assert [100, 200, 30, 40, 500].intersect(ylist) == [30, 40]
//or the disjoint
assert [100, 200, 300, 400, 500].disjoint(ylist)


//empty lists are "FALSE"
if ([]) assert false

//lists can be treated as stacks (from the front of the list)
assert ylist.pop() == 10
assert ylist == [20, 30, 40, 50]
ylist.push(10) //returns true or false
assert ylist == [10, 20, 30, 40, 50] //back to normal

//lists of lists can be flattened
assert [[1, 2, 3], [4, 5, 6], [7, 8]].flatten() == [1, 2, 3, 4, 5, 6, 7, 8]


//sorting is good
def klist = [2, 4, 4, 0, 1, 5, 6, 8, 1, 10]
assert klist.reverse() == [10, 1, 8, 6, 5, 1, 0, 4, 4, 2]

assert klist.sort() == [0, 1, 1, 2, 4, 4, 5, 6, 8, 10]

//SORT by second letter
assert ['sun', 'sand', 'sea', 'boat'].sort { i, j ->
  i[1] <=> j[1]
} == ['sand', 'sea', 'boat', 'sun']

//things like map etc
assert klist.collect { item ->
  item * item
} == [0, 1, 1, 4, 16, 16, 25, 36, 64, 100]
// and findall is like filter
assert klist.findAll { item ->
  item == 1
} == [1, 1]
// and inject a bit like reduce
assert klist.inject(0) { accumulator, item ->
  accumulator + item
} == klist.sum()


//querying our lists
def glist = [3, 4, 5]
assert glist.head() == glist.first()
assert glist.tail() == [4,5]
assert glist.last() == 5

assert glist.find { item ->
  item % 2 == 0
} == 4

assert glist.every { item ->
  item < 6
}


// and a hack
// just a little program to convert km/h to min/km
  println("Speed\t\t\tPace")
  1.step(10, 0.5) { kmPerHr ->
    pace = (60f / (kmPerHr)).round(1)
    println("$kmPerHr km/hr \t $pace min/km")
  }

