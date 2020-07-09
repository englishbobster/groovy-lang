package stu.groovyinaction

AGroovyClass groovyClass = new AGroovyClass(10, "this was a stringField", new File("here/there/somewhere"))
println(groovyClass.toString())

//we can instantiate like so as well
def anotherGroovyClass = [10, "String", new File("whooha")] as AGroovyClass
AGroovyClass yetAnotherGroovyClass = [10, "String", new File("whooha")]
assert yetAnotherGroovyClass.stringField == anotherGroovyClass.stringField

//so at this stage we have typed "somefield"
// notice we can use dot.notation to reach the fields
assert groovyClass.someField.class.name == 'java.io.File'
//but we can reassign?
groovyClass.setSomeField(new Date())
assert groovyClass.someField.class.name == 'java.util.Date'

//access the fields via subscript...dynamically
def fieldName = 'someDate'
def fieldValue = groovyClass[fieldName]

groovyClass.stringField = "another string"
assert groovyClass.butNoType == 0

//should get the date field
println("***  $fieldValue ***")

//test method with defaults (3rd parameter has default value 3
assert groovyClass.methodWithDefaults(1, 4) == 8
assert groovyClass.methodWithListArguments([1, 4, 2, 1]) == 8
assert groovyClass.methodWithOptionals(1, 4, 6, 7) == 18
assert groovyClass.methodWithMap(a: 1, b: 2, c: 10, d: 7) == 20

//using named parameters to a constructor
class Named {
  String big
  String small
}

def aNamedClass = new Named(big: "its big")
assert aNamedClass.big == "its big"
assert aNamedClass.small == null

//and Constructor params as a list
class ListCtr {
  String big
  String small

  ListCtr(String big, String small) {
    this.big = big
    this.small = small
  }
}

ListCtr listCtr = ["sigh", "sing"]
assert listCtr.big == "sigh"
assert listCtr.small == "sing"

