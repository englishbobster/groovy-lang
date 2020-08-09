def move(string, distance) {
  string.collect { c -> (c as char) + distance as char}.join()
}

String.metaClass {
  shift = -1
  encode { -> move delegate, shift}
  decode { -> move delegate, -shift}
  getCode { -> encode()}
  getOrig { -> decode()}
}
def ibm = "IBM"

assert ibm.encode() == "HAL"
assert "HAL".decode() == "IBM"

ibm.shift = 7
assert ibm.code == "PIT"

ibm.shift = 1

(-5..15).each {
  ibm.shift = it
  println(ibm.code)
}
