//using lists to implement Quicksort
//and it will be ascending due to left(>) and right(<)
def quicksort(list) {
  if (list.size() < 2) {
    return list
  }
  def pivot = list[list.size().intdiv(2)]
  def left = list.findAll { item ->
    item > pivot
  }
  def middle = list.findAll { item ->
    item == pivot
  }
  def right = list.findAll { item ->
    item < pivot
  }
  return quicksort(left) + middle + quicksort(right)
}

assert quicksort([101, 7, 564, 7, 2, 5, 76, 1337, 434, 345, 21]) ==
    [1337, 564, 434, 345, 101, 76, 21, 7, 7, 5, 2]

//duck typed list
assert quicksort([1.0f, 'a', 10, null]) == ['a', 10, 1.0, null]
//we can event sort a string because it implements size, getAt, and findAll
assert quicksort('ytehsjkc') == ['y', 't', 's', 'k', 'j', 'h', 'e', 'c']
