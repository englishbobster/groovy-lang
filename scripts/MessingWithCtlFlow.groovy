//Conditionals and Groovy truth
//lists, maps, and strings can be tested
if (![] && ![:] && !'') { //empty things are false
  assert true
} else {
  assert false
}

//conditional operator
def result = ('a' =~ /a/) ? 'passed' : 'failed' // a matching regex is true
assert result == 'passed'

//elvis...use the test expression if true
def otherResult = [a: 1] ?: "default" //if the map has values return the map
assert otherResult == [a: 1]

//oh no switch...but its better than java's
//switch on different classifiers
switch (10) {
  case 0: println '0'; break
  case 0..9: println '0..9'; break
  case [8, 9, 11]: println '[8,8,11]'; break
  case Float: println 'Float'; break
  case { it -> it % 3 == 0 }: println 'divisible by 3'; break
  case ~/../: println '2 digits'; break
  default: println "default case"; break
}
