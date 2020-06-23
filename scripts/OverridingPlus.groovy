import groovy.transform.Immutable

//Immutable gives a value based implementation of equals
@Immutable //This is going to override equal (we have a value class!)
class Money {
  int ammount
  String currency

  //This going to override '+'
  Money plus(Money other) {
    if (null == other) return this
    if (other.currency != currency) {
      throw new IllegalArgumentException(
          "Cannot add different currencies: $other.currency to $currency}"
      )
    }
    return new Money(ammount + other.ammount, currency)
  }

  //we can overload plus as well
  Money plus(Integer more) {
    def toAdd = new Money(more, currency)
    return this + toAdd
  }

}

Money buck  = new Money(1, 'USD')
assert buck
assert buck == new Money(1, 'USD')
assert buck + buck == new Money(2, 'USD') //SEE HERE

assert buck + 1 == new Money(2, 'USD')
