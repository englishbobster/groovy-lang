import java.util.function.Function

/**
 * We want to write a function that generates accumulators:
 * a function that takes a number n, and returns a function
 * that takes another number i and returns n incremented by i.
 */

def nFunction(n) {
  return {i -> n + i}
}

def iFunction = nFunction(6)
assert iFunction(3) == 9
assert iFunction(2 ) == 8
