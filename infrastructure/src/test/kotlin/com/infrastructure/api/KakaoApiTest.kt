package com.infrastructure.api

import com.searchblog.infrastructure.api.KakaoApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

class KakaoApiTest {
  private lateinit var mockWebServer: MockWebServer
  private lateinit var kakaoApi: KakaoApi

  @BeforeEach
  fun setUp() {
    mockWebServer = MockWebServer()
    mockWebServer.start()

    kakaoApi = KakaoApi(
      webClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build(),
    )
  }

  @AfterEach
  fun tearDown() {
    mockWebServer.shutdown()
  }

  @Test
  fun getSearchBlog() {
    // given
    val fakeResponse = """
{
    "documents": [
        {
            "blogname": "그맛이 알고싶다",
            "contents": "#<b>부산</b>중국집 #<b>부산</b>이즈비기닝 11월 마지막주 <b>부산</b> 동의과학대학교쪽에 볼일이 있어서 다녀오던 길에 평소 궁금해하고 리스트에 노미네이트되어 있던 <b>부산</b> <b>맛집</b> 베스트에 다녀왔어요 미리 운영시간을 확인하고 저녁에 방문했는데요 편하게 혼자 대관할 수 있었어요. 1. <b>부산</b> <b>맛집</b> 백운반점 위치 ​ 지하철 3호선 , 4...",
            "datetime": "2023-12-08T08:37:08.000+09:00",
            "thumbnail": "https://search1.kakaocdn.net/argon/130x130_85_c/G93MqgxAPua",
            "title": "<b>부산</b> 동래 <b>맛집</b> 백운반점 노포 중국집 <b>부산</b>이즈비기닝",
            "url": "https://blu2sky7.tistory.com/1128"
        },
        {
            "blogname": "국내여행의 모든것",
            "contents": "<b>부산</b> <b>맛집</b> 베스트 10, 해운대, 동래구, 광안리, 서면, 그리고 경성대 <b>맛집</b>까지 <b>부산</b>에 걸친 현지인 추천 찐맛집들로 구성해 보았습니다. 음식 종류도 다양하게 선정해 보았는데, 10군데 중에서 다는 아니더라도 적어도 9군데는 꼭 가보시고 싶을 거라 예상합니다. 메뉴, 가격, 위치까지 자세히 안내드리겠습니다. 길...",
            "datetime": "2023-11-18T00:48:26.000+09:00",
            "thumbnail": "https://search3.kakaocdn.net/argon/130x130_85_c/JWopgvXVhsT",
            "title": "<b>부산</b> <b>맛집</b> 베스트 10(현지인이 추천하는 찐맛집들!)",
            "url": "https://c.energyforaday.com/54"
        }
    ],
    "meta": {
        "is_end": false,
        "pageable_count": 794,
        "total_count": 4611396
    }
}
    """.trimIndent()

    mockWebServer.enqueue(
      MockResponse()
        .setResponseCode(200)
        .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .setBody(fakeResponse)
    )


    runBlocking {
      // when
      val response = kakaoApi.getSearchBlog(
        searchWord = "대구 맛집",
        sort = "",
        page = 1,
        size = 1,
      )


      println("")
      assertThat(response?.meta?.totalCount).isEqualTo(4611396)
    }
  }

  @Test
  fun getSearchBlog4xxError() {
    // given

    mockWebServer.enqueue(
      MockResponse()
        .setResponseCode(400)
    )
    runBlocking {
      // when
      val response = kakaoApi.getSearchBlog(
        searchWord = "대구 맛집",
        sort = "",
        page = 1,
        size = 1,
      )

      assertThat(response).isEqualTo(null)
    }
  }

  @Test
  fun getSearchBlog5xxError() {
    // given

    mockWebServer.enqueue(
      MockResponse()
        .setResponseCode(501)
    )
    runBlocking {
      // when
      val response = kakaoApi.getSearchBlog(
        searchWord = "대구 맛집",
        sort = "",
        page = 1,
        size = 1,
      )

      assertThat(response).isEqualTo(null)
    }
  }
}