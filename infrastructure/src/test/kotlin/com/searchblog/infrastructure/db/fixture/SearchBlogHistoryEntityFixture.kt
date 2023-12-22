package com.searchblog.infrastructure.db.fixture

import com.searchblog.infrastructure.db.entity.SearchBlogHistoryEntity
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime

class SearchBlogHistoryEntityFixture {

  companion object {
    private val faker = Faker()

    fun create(
      id: Long? = null,
      keyword: String = faker.random.randomString(20),
      modifiedAt: LocalDateTime = LocalDateTime.now(),
      createdAt: LocalDateTime = LocalDateTime.now(),
    ): SearchBlogHistoryEntity {
      return SearchBlogHistoryEntity(
        id = id,
        keyword = keyword,
        modifiedAt = modifiedAt,
        createdAt = createdAt,
      )
    }
  }
}