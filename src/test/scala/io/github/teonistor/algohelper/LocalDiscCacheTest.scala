package io.github.teonistor.algohelper

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock._
import org.apache.commons.io.FileUtils.deleteDirectory
import org.mockito.BDDMockito.`given`
import org.mockito.Mockito.{mock, times, verify}
import org.scalatest.funsuite.AnyFunSuiteLike

import java.io.File
import java.nio.file.Files.readString

class LocalDiscCacheTest extends AnyFunSuiteLike {
  private val file = "target/tmp/cachefile"

  test("web source") {
    deleteDirectory(new File(file).getParentFile)
    val server = new WireMockServer(0)
    val path = "/data/1234"
    val data = "Some data"
    server.start()
    server.stubFor(get(path).willReturn(ok(data)))

    assert(LocalDiscCache.webSource(file, s"localhost:${server.port()}$path", "some-key"->"some-value") == data)
    server.verify(1, getRequestedFor(urlPathEqualTo(path)).withCookie("some-key", equalTo("some-value")))
    server.resetAll()
    assert(LocalDiscCache.webSource(file, s"localhost:${server.port()}$path", "some-key"->"some-value") == data)
    server.stop()
  }

  test("computation source, check dir") {
    deleteDirectory(new File(file).getParentFile)
    val computation = mock(classOf[() => String])
    val data = "Some more data"
    given(computation()).willReturn(data)

    assert(LocalDiscCache.computationSource(file, computation()) == data)
    assert(LocalDiscCache.computationSource(file, computation()) == data)
    verify(computation, times(1))()

    assert(readString(new File(file).getParentFile.toPath.resolve(".gitignore")) == "*")
  }

  test("computation source, if writing to file fails we should still get the result") {
    deleteDirectory(new File(file).getParentFile)
    new File(file).mkdirs()

    val computation = mock(classOf[() => String])
    val data = "Some other data"
    given(computation()).willReturn(data)

    assert(LocalDiscCache.computationSource(file, computation()) == data)
    assert(LocalDiscCache.computationSource(file, computation()) == data)
    verify(computation, times(2))()
  }
}
