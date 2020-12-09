package io.github.teonistor.func

// This should make it into our library of utils
object Utils {

  implicit class HasLet[T <: Any](obj: T) {

    def let[U <: Any](func: T => U): U = func(obj)
  }
}