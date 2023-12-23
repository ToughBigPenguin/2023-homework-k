package com.searchblog.infrastructure.api

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient


class NaverApiTest {
  private lateinit var mockWebServer: MockWebServer
  private lateinit var naverApi: NaverApi

  @BeforeEach
  fun setUp() {
    mockWebServer = MockWebServer()
    mockWebServer.start()

    naverApi = NaverApi(
      webClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build(),
    )
  }

  @AfterEach
  fun tearDown() {
    mockWebServer.shutdown()
  }

  @Test
  fun getNaverSearchBlogTest() {
    // given
    val fakeResponse = """
{
    "lastBuildDate": "Sat, 23 Dec 2023 19:38:19 +0900",
    "total": 984819,
    "start": 1,
    "display": 10,
    "items": [
        {
            "title": "흔한 백수의 일상 | 인간관계 고찰,<b>대구맛집</b> 7곳,입술 변신... ",
            "link": "https://blog.naver.com/jiji_goguma_/223300051187",
            "description": "라뷰 #삼덕동맛집 #대구브런치맛집 저녁시간 전까지 열심히 개인운동하구요 사촌동생 만나서 좀... 기절 #<b>대구맛집</b> 담날 또 엄마 기사노릇하러 구미 드라이브를 왔다 온 김에 카페 하나 도장깨려고 찾아온 곳은... ",
            "bloggername": "굼굼블로그",
            "bloggerlink": "blog.naver.com/jiji_goguma_",
            "postdate": "20231222"
        },
        {
            "title": "더현대 <b>대구 맛집</b> 퍼부어 베트남 음식 맛 그대로",
            "link": "https://blog.naver.com/lucky13/223239996262",
            "description": "더현대 <b>대구 맛집</b>에서 현지 느낌을 맛볼 수 있는 퍼부어가 들어왔다고 해서 어제저녁 먹으러... 맛있더라고요 대구 더현대 맛집 퍼부어는 메뉴 하나하나가 다 맛있어서 세 가지다 성공적이었어요 짜조도... ",
            "bloggername": "찌뽕&나뽕이 세상",
            "bloggerlink": "blog.naver.com/lucky13",
            "postdate": "20231018"
        }
    ]
}
    """.trimIndent()

    // when
    mockWebServer.enqueue(
      MockResponse()
        .setResponseCode(200)
        .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        .setBody(fakeResponse)
    )

    // then
    runBlocking {
      // when
      val response = naverApi.getSearchBlog(
        searchWord = "대구 맛집",
        sort = "",
        page = 1,
        size = 1,
      )


      println("")
      Assertions.assertThat(response?.total).isEqualTo(984819)
    }
  }
}