def boxer = new Expando()

//note these fields/properties dont exist yet
boxer.takeThis = 'ouch!'
boxer.fightBack = { times -> takeThis * times}

assert boxer.fightBack(3) == "ouch!ouch!ouch!"


//and the Expando Metaclass
assert String.metaClass =~ /MetaClassImpl/

try {
  "StuarT".low() == "stuart"
} catch (MissingMethodException e) {
  assert true
}

//add new method to String
String.metaClass.low = {/* no params in this closure */ -> delegate.toLowerCase()}
//Expando metaclass automatically assigned since we added a new method
assert String.metaClass =~ /ExpandoMetaClass/

assert "StuarT".low() == "stuart"
