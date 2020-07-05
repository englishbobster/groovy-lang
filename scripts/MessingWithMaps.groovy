//notice the keys are strings (can leave out ' or ")
def aMap = [a: 1, b: 2, c: 3]
def emptyMap = [:]
assert aMap instanceof LinkedHashMap
assert aMap.size() == 3
assert emptyMap.size() == 0

// a putAll syntax (feel free to use putAll but...)
assert [*: aMap, d: 4] == [a: 1, b: 2, c: 3, d: 4]

//accessing maps
assert aMap['a'] == 1
assert aMap.b == 2
assert aMap.get('c') == 3
assert aMap.f == null //no value
assert aMap.get('f', 8) == 8 //get with default

//but we can insert values
aMap['f'] = 32
assert aMap.f == 32

//query a map using anything other than JDK methods
assert aMap.any { entry ->
  entry.value > 33 // f:32 is the highest value
} == false

assert aMap.every { entry ->
  entry.key < 'd' //because we have 'f'
} == false

assert aMap.keySet() == ['a', 'b', 'c', 'f'] as Set
assert aMap.values().toList() == [1, 2, 3, 32]

//manipulating maps
//just check that the original map is ..

//remove
aMap.remove('f')
assert aMap == [a: 1, b: 2, c: 3]
aMap = aMap + [f: 32]
assert aMap == [a: 1, b: 2, c: 3, f: 32]

def a_bMap = aMap.subMap('a', 'b')
assert a_bMap == [a: 1, b: 2]

assert aMap.findAll { entry ->
  entry.value < 3
} == a_bMap

assert aMap.find { entry ->
  entry.value == 32
} == LinkedHashMap.entry("f", 32) //the entry returned

assert aMap.collect { entry ->
  entry.value *= 2
} == [2, 4, 6, 64] // a list is returned

