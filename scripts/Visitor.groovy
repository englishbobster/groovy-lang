class Drawing {
  List shapes

  def accept(Closure yield) {
    shapes.each { it ->
      it.accept(yield)
    }
  }
}

class Shape {
  def accept(Closure yield) {
    yield(this)
  }
}

class Square extends Shape {
  def width

  def area() {
    width * width
  }
}

class Circle extends Shape {
  def radius

  def area() {
    (radius * radius) * Math.PI
  }
}

def piccy = new Drawing(shapes: [
    new Square(width: 1),
    new Circle(radius: 1)
])

//the following closures are our visiting business logic
def total = 0
piccy.accept { total += it.area() }

println("Total area of shapes: $total")
println 'Individually: '
piccy.accept { println it.class.name + ":" + it.area() }
