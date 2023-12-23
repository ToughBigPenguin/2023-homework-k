package com.searchblog.infrastructure.api

import com.searchblog.infrastructure.api.dto.KakaoBlogResponse
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
    try {
      return webClient.get()
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
        .awaitBody()
    } catch (e: WebClientException) {
      logger.warn { "KAKAO API ERROR, message : ${e.message}" }
    }

    return null
  }
}
