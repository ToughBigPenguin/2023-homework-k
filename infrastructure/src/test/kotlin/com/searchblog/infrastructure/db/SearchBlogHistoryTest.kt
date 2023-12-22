package com.searchblog.infrastructure.db

import com.searchblog.infrastructure.db.fixture.SearchBlogHistoryEntityFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class SearchBlogHistoryTest(
  @Autowired private val searchBlogHistoryRepository: SearchBlogHistoryRepository,
) {

  @Test
  fun save() {
    // given
    searchBlogHistoryRepository.saveAll(
      listOf(
        SearchBlogHistoryEntityFixture.create(
          keyword = "카카오"
        ),
        SearchBlogHistoryEntityFixture.create(
          keyword = "다음"
        ),
        SearchBlogHistoryEntityFixture.create(
          keyword = "카카오"
        ),
      )
    )

    // when
    val actual = searchBlogHistoryRepository.findAll()

    // then
    assertThat(actual.size).isEqualTo(3)
  }

  @Test
  fun findGroupByKeywordTop10() {
    // given
    val saveSearchBlogHistory = mutableListOf(
      SearchBlogHistoryEntityFixture.create(keyword = "카카오"),
      SearchBlogHistoryEntityFixture.create(keyword = "카카오"),
      SearchBlogHistoryEntityFixture.create(keyword = "카카오"),
      SearchBlogHistoryEntityFixture.create(keyword = "다음"),
      SearchBlogHistoryEntityFixture.create(keyword = "다음"),
    )

    // sample 30개
    for (i in 0 until 30) {
      saveSearchBlogHistory.add(SearchBlogHistoryEntityFixture.create())
    }

    searchBlogHistoryRepository.saveAll(saveSearchBlogHistory)

    // when
    val actual = searchBlogHistoryRepository.findGroupByKeywordTop10()

    // then
    assertThat(actual.size).isEqualTo(10)
    assertThat(actual[0].keyword).isEqualTo("카카오")
    assertThat(actual[0].count).isEqualTo(3)
    assertThat(actual[1].keyword).isEqualTo("다음")
    assertThat(actual[1].count).isEqualTo(2)
  }
}