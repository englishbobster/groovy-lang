import org.codehaus.groovy.runtime.InvokerHelper

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
stringMeta.properties.each {
  println("the properties of toString are:" + it.getName())
}

println("nr of methods: " + stringMeta.methods.size())
stringMeta.methods.each {
  println("the methods in toString are:" + it.toString())
}
println("nr of meta methods: " + stringMeta.metaMethods.size())

//invoke method
assert stringMeta.invokeMethod("", "toString", NO_ARGS) == ""
//invoke string constructor and set tests...new String("tests")
assert stringMeta.invokeConstructor("tests") == "tests"

//Demonstration of the proxymetaclass

class InspectMe {
  int outer() {
    return inner()
  }

  private int inner() {
    return 1
  }
}

def tracer = new TracingInterceptor(writer: new StringWriter())
def proxyMetaClass = ProxyMetaClass.getInstance(InspectMe)

//set the tracer as an interceptor
proxyMetaClass.interceptor = tracer

InspectMe inspectMe = new InspectMe()
inspectMe.metaClass = proxyMetaClass

assert inspectMe.outer() == 1

assert "\n" + tracer.writer.toString() == """
before InspectMe.outer()
  before InspectMe.inner()
  after  InspectMe.inner()
after  InspectMe.outer()
"""

//Modifying the metaclass
class TobeModded {

}

def before = new TobeModded()

//All instances will be modded
TobeModded.metaClass.myProp = "TobeModded property"
TobeModded.metaClass.test = {/*closure with no params*/ -> myProp}

//before was instantiated before modifying the metaclass
try {
  before.test()
} catch (MissingMethodException mme) {
  assert true, "should throw missingMethodException"
}

assert new TobeModded().test() == "TobeModded property"

//we can do the same PER INSTANCE if we use the instance name
def theInstance = new TobeModded()
theInstance.metaClass.myProp = "instance property"
theInstance.metaClass.test = {/*closure with no params*/ -> myProp}

try {
  theInstance.test()
} catch (MissingMethodException mme) {
  assert true, "should throw missingMethodException"
}


