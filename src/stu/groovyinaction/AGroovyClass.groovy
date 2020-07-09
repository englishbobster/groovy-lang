package stu.groovyinaction

class AGroovyClass {
  private publicField //no type but access modifier...wow!
  String stringField //we can type if we want
  def someField  //so untyped but can we use this
  def someDate = new Date()

  private static butNoType //so static fields

  private static final CONSTANT = "A_CONSTANT"
  private static final INT_CONSTANT = 10

  AGroovyClass(publicField, String stringField, someField) {
    this.publicField = publicField
    this.stringField = stringField
    this.someField = someField
    butNoType = 0
  }

  def setSomeField(value) {
    someField = value
  }

  def getSomeField() {
    return someField
  }

  //the way to implement/override the subscript operators to get fields
  Object get(String name) {
    butNoType ++
    println("doing some get stuff in when we use []")
    return 'whatever'
  }

  void set(String name, Object value) {
    butNoType ++
    println("doing some set stuff in when we use []")
  }

  def methodWithDefaults(a, b, c = 3) {
    return a + b + c
  }

  def methodWithListArguments(List args) {
    args.inject(0) { sum, i ->
      sum += i
    }
  }

  def methodWithOptionals(a, b, Object[] optionals) {
    return a + b + methodWithListArguments(optionals.toList())
  }

  @Override
  String toString() {
    butNoType ++
    "This object:\n" +
        "$publicField\n" +
        "$stringField\n" +
        "$someField\n" +
        "$someDate\n" +
        "$butNoType"
  }

  def methodWithMap(Map args) {
    ['a','b','c','d'].each { it ->
      args.get(it, 0)
    }
      return args.a + args.b + args.c + args.d
  }
}
