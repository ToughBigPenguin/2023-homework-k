package com.searchblog.infrastructure.api

import java.time.Duration
import mu.KotlinLogging
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient

@Component
class KakaoApi(
  private val webClient: WebClient = WebClient.builder()
    .baseUrl("https://dapi.kakao.com") // TODO : 상수로 변경하면 좋을듯
    .clientConnector(
    ReactorClientHttpConnector(
      HttpClient.create().responseTimeout(Duration.ofSeconds(3))
    )
  )
    .defaultHeader("Authorization", "KakaoAK 3027ae5b298c87dad0be1c8464b4b094") // TODO : 상수로 변경하면 좋을듯
    .build(),
) {

  val logger = KotlinLogging.logger { }

  suspend fun getSearchBlog(
    searchWord: String,
    sort: String,
    page: Int,
    size: Int,
  ): KakaoBlogResponse? {
    // TODO : 타임아웃발생시 어떻게 할것인지?
    val response: KakaoBlogResponse? = webClient.get()
      .uri { uribulider ->
        uribulider
          .path("/v2/search/blog")
          .queryParam("query", searchWord)
          .queryParam("sort", sort)
          .queryParam("page", page)
          .queryParam("size", size)
          .build()
      }
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(HttpStatusCode::is4xxClientError) {
        logger.warn { "KAKAO API CALL ERROR, status : ${it.statusCode()}" }
        Mono.empty()
      }
      .onStatus(HttpStatusCode::is5xxServerError) {
        logger.warn { "KAKAO API CALL ERROR, status : ${it.statusCode()}" }
        Mono.empty()
      }
      .awaitBodyOrNull()


    return response
  }
}
