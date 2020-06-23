import groovy.transform.Immutable

String GINA_TITLE = 'Groovy in Action'

@Immutable
class FixedBook {
  String title
}
def gina = new FixedBook(GINA_TITLE)
def regina = new FixedBook(title: GINA_TITLE)

assert gina.title == GINA_TITLE
assert gina == regina

try {
  gina.title = "Oops!"
  assert false, "shouldn't get here"
} catch (ReadOnlyPropertyException expectThis) {
  println("We tried to change the immutable fixed book!: '$expectThis.message'")
}
