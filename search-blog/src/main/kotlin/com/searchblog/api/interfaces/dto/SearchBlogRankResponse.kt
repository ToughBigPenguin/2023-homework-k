package com.searchblog.api.interfaces.dto

import com.searchblog.api.domain.SearchBlogRank

class SearchBlogRankResponse(
  val keyword: String,

  val count: Long,
) {

  companion object {
    fun of(searchBlogRanks: List<SearchBlogRank>): List<SearchBlogRankResponse> {
      return searchBlogRanks.map {
        SearchBlogRankResponse(
          keyword = it.keyword,
          count = it.count,
        )
      }
    }
  }
}