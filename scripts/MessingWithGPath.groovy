//groovy path is something like this
def matching = ~/get.*/
println this.class.methods.name.grep(matching).sort()

//can we do this for a java object?..yes
println Date.class.methods.name.grep(matching).sort()

//Invoice example for gpath
class Invoice {
  List items
  Date date
}

class LineItem {
  Product product
  int count

  int total() {
    return product.dollar * count
  }
}

class Product {
  String name
  def dollar
}

def ulcDate = Date.parse('yyy-MM-dd', '2015-01-01')
def otherDate = Date.parse('yyy-MM-dd', '2015-02-02')
def ulc = new Product(dollar: 1499, name: 'ULC')
def ve = new Product(dollar: 499, name: 'Visual Editor')

def invoices = [
    new Invoice(date: ulcDate, items: [
        new LineItem(count: 5, product: ulc),
        new LineItem(count: 1, product: ve)
    ]),
    new Invoice(date: otherDate, items: [
        new LineItem(count: 4, product: ve)
    ])
]

//get the items and then flatten to a list of lineItems ....
def allItems = invoices.items.flatten()

//the list spread operator (*.) to total each Invoice
assert [5 * 1499, 499, 4 * 499] == allItems*.total()
//which would be the same as mapping/collecting (remember ?. is null check)
assert [5 * 1499, 499, 4 * 499] == allItems.collect{ it?.total()}

//the other spread operator(without the '.')
assert [3,5,6] == [3,*[5,6]]
assert [a:1, b:2, c:3] == [a:1, *:[b:2, c:3]]

//find the product names in the object graph where the total Invoice price is > 7000
assert ['ULC'] == allItems.grep { it ->
  it.total() > 7000
}.product.name

//query the dates in the invoices
def searchDates = invoices.grep {
  it.items.any {
    it.product == ulc
  }
}.date*.toString()

assert [ulcDate.toString()] == searchDates

//chain commands
