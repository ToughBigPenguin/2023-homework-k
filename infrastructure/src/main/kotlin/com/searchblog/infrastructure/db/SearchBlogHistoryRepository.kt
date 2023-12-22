package com.searchblog.infrastructure.db

import com.searchblog.infrastructure.db.dto.KeywordRank
import com.searchblog.infrastructure.db.entity.SearchBlogHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SearchBlogHistoryRepository : JpaRepository<SearchBlogHistoryEntity, Long> {

  @Query(
    "select " +
     "new com.searchblog.infrastructure.db.dto.KeywordRank(searchBlogHistory.keyword, count(searchBlogHistory)) " +
     "from SearchBlogHistoryEntity as searchBlogHistory " +
     "group by searchBlogHistory.keyword " +
     "order by count(searchBlogHistory) desc " +
     "limit 10"
  )
  fun findGroupByKeywordTop10(): List<KeywordRank>
}