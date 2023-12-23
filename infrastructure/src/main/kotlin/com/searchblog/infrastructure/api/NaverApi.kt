package com.searchblog.infrastructure.api

import com.searchblog.infrastructure.api.dto.NaverBlogResponse
import java.time.Duration
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientException
import org.springframework.web.reactive.function.client.awaitBody
import reactor.netty.http.client.HttpClient

@Component
class NaverApi(
  private val webClient: WebClient = WebClient.builder()
    .baseUrl("https://openapi.naver.com") // TODO : 상수로 변경하면 좋을듯
    .clientConnector(
      ReactorClientHttpConnector(
        HttpClient.create().responseTimeout(Duration.ofSeconds(3))
      )
    )
    .defaultHeader("X-Naver-Client-Id", "1brX6ToTKMVHkR2OBy_d") // TODO : 상수로 변경하면 좋을듯
    .defaultHeader("X-Naver-Client-Secret", "KWTbsvmUfX") // TODO : 상수로 변경하면 좋을듯
    .build(),
) {

  val logger = KotlinLogging.logger { }

  suspend fun getSearchBlog(
    searchWord: String,
    sort: String,
    page: Int,
    size: Int,
  ): NaverBlogResponse? {

    try {
      return webClient.get()
        .uri { uribulider ->
          uribulider
            .path("v1/search/blog.json")
            .queryParam("query", searchWord)
            .queryParam("display", size)
            .queryParam("start", page)
            .queryParam("sort", sort)
            .build()
        }
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .awaitBody()
    }  catch (e: WebClientException) {
      logger.warn { "NAVER API ERROR, message : ${e.message}" }
    }

    return null
  }
}