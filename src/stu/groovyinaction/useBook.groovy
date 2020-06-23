import stu.groovyinaction.Book

String title = 'Groovy in Action'

//The class definition could have been added in this file
/*
class Book {
  String title
} //would have been enough
 */
Book gina = new Book(title)

assert gina.getTitle() == title
assert gina.title == title
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

String getTitleBackwards(book) {
  String title = book.getTitle()
  return title.reverse()
}
