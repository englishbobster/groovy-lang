//The Method Object Protocol ...allows us to modify/add any object behaviour by overriding/using hooks in the MOP

//override methodMissing
class Pretender {

  //would normally throw a MissingMethodException
  def methodMissing(String name, Object args) {
    "called $name with $args"
  }
  //would normally throw a MissingPropertyException
  def propertyMissing(String name) {
    "accessed $name"
  }

}

def pretender = new Pretender()
//try missing method
assert pretender.hello("world") == 'called hello with [world]'
//try missing property
assert pretender.hi == 'accessed hi'

//those default exceptions missingMethod
def date = new Date()
try {
  date.gimball()
} catch (MissingMethodException e) {
  println(e.getClass().name)
  assert true
}
//missingProperty
try {
  def t = date.poop
} catch (MissingPropertyException e) {
  println(e.getClass().name)
  assert true
}

//changing logic at runtime

class AnotherPretender {
  Closure whatToDo = {name -> "this $name has been accessed"}

  def propertyMissing(String name) {
    whatToDo (name)
  }
}

//a missing property
def anotherPretender = new AnotherPretender()
assert anotherPretender.missingProp == "this missingProp has been accessed"

//change the closure in the class
anotherPretender.whatToDo = {name -> "the size of the property name $name is: ${name.size()}"}
assert anotherPretender.missingProp == "the size of the property name missingProp is: 11"


//messing with the metaclass
MetaClass stringMeta = String.metaClass
final Object[] NO_ARGS = []
println("toString: " + stringMeta.respondsTo("toString", NO_ARGS).size())
println("nr of properties: " + stringMeta.properties.size())
println("nr of methods: " + stringMeta.methods.size())
println("nr of meta methods: " + stringMeta.metaMethods.size())

//invoke method
assert stringMeta.invokeMethod("", "toString", NO_ARGS) == ""
assert stringMeta.invokeConstructor("tests") == "tests"
