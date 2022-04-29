package io.github.teonistor.weirdness

import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.scalatest.funsuite.AnyFunSuite

class ScalaWildcards extends AnyFunSuite {

  test("compile with explicit type") {
    val a = new SimpleClass()
    val b = new SimpleClass()
    val c = new SimpleClass()
    val wildcarding = mock(classOf[WildcardingType])

    /* Discovered this weirdness in the context of assertThat(iterable), but this is the essence:
     * Would expect the following lines to compile, as in Java:
     *    given(wildcarding.method).willReturn(Set(a, b))
     *    parameterisedMethod(wildcarding.method).accept(c)
     * But Scala (tried 2.13.4, 2.13.8) gives error:
     *    type mismatch;
     *      found   : b.type (with underlying type ScalaWildcards.this.SimpleClass)
     *      required: _$1 where type _$1 <: ScalaWildcards.this.SimpleClass
     * It only works with a cast (which the editor claims is redundant! -Build #IC-221.5080.210, Scala plugin 2022.1.13):
     */

    given(wildcarding.method.asInstanceOf[Iterable[SimpleClass]]).willReturn(Set(a, b))
    parameterisedMethod(wildcarding.method.asInstanceOf[Iterable[SimpleClass]]).accept(c)
  }

  class SimpleClass {}

  trait WildcardingType {
    def method: Iterable[_ <: SimpleClass]
  }

  class ParamterisedClass[T](input: Iterable[T]) {
    def accept(t: T): Unit =
      println(t)
  }

  private def parameterisedMethod[T](input: Iterable[T]) =
    new ParamterisedClass(input)
}
