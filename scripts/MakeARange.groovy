def drone = new Invader("DRONE")
def plat = new Invader("PLATINUM")


(drone..plat).each { invader ->
  println invader.toString()
}

//to use ranges, implement comparable, previous and next
class Invader implements Comparable {

  static final INVADER_NAMES = [
      'DRONE',
      'HUNTER',
      'JAEGER',
      'CONTROL',
      'ALPHA',
      'PLATINUM'
  ]

  private int index = 0

  Invader(String name) {
    index = INVADER_NAMES.indexOf(name)
  }

  //modulo % to roll over
  Invader next() {
    return new Invader(INVADER_NAMES[(index + 1) % INVADER_NAMES.size()])
  }

  //no modulo here... if negative index we roll back through the list
  Invader previous() {
    return new Invader(INVADER_NAMES[index - 1])
  }

  @Override
  int compareTo(Object that) {
    //spaceship operator...implies gt, equal, lt
    return this.index <=> that.index
  }

  @Override
  String toString() {
    return INVADER_NAMES[index]
  }
}
