//inclusive ranges:
(0..9).each { i ->
  print(i)
}
//exclusive ranges:
(0..<10).each { i ->
  print i
}

assert (0..9) == (0..<10)

//explicit construction
assert (0..9) == new IntRange(0, 9)

//dates as well can be ranges
def today = new Date()
def yesterday = today - 1
assert (yesterday..today).size() == 2
print('\n' + today + '\n')

//strings as ranges
('a'..'z').each { letter ->
  print(letter)
}

// we can do for-in-range
def output = ''
for (e in 0..9) {
  output += e
}
assert output == '0123456789'

//classification with ranges
def age = 50
def result = ''
switch (age) {
  case 1..17: result = 'still a child'; break
  case 18..30: result = 'growing growing'; break
  case 31..49: result = 'not there yet'; break
  case 50..100: result = 'the best age'; break
}
assert result == 'the best age'

//and filtering with ranges
def ages = [20, 36, 50, 65]
def bestAge = 50..60
assert ages.grep(bestAge) == [50]
