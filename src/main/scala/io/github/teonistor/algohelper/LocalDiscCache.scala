package io.github.teonistor.algohelper

import org.springframework.web.reactive.function.client.WebClient

import java.io.File
import java.nio.file.Files.{readString, writeString}
import java.nio.file.Path
import scala.util.Try

object LocalDiscCache {

  def webSource(file: String, uri: String, cookies: (String,String)*): String =
    computationSource(file, {
      val webClient = WebClient.builder()
        .codecs(_.defaultCodecs().maxInMemorySize(1024 * 1024 * 1024))
        .build().get()
      webClient.uri(uri)
      cookies.foreach((webClient.cookie _).tupled)

      webClient.retrieve()
        .bodyToMono(classOf[String])
        .block
    })

  def computationSource(file: String, computation: => String): String =
    Try(readString(Path.of(file)))
      .recover { e =>
        println(s"Note: Could not load cache from $file because $e")
        val data = computation

        Try {
          mkdirsGitignoredIfNeeded(new File(file).getParentFile)
          writeString(Path.of(file), data)
        }.recover(e => println(s"Note: Could not save cache to $file because $e"))

        data
      }.get

  private def mkdirsGitignoredIfNeeded(dir: File) =
    if (dir.mkdirs())
      writeString(dir.toPath.resolve(".gitignore"), "*")
}
