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
}