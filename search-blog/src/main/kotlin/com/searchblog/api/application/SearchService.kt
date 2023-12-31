package com.searchblog.api.application

import com.searchblog.api.domain.Search
import com.searchblog.api.domain.SearchBlogRank
import com.searchblog.api.interfaces.dto.SearchBlogRequest

interface SearchService {
  fun search(searchBlogRequest: SearchBlogRequest): Search

  fun saveSearchBlogKeyword(search: String)

  fun getSearchBlogKeywordRank(): List<SearchBlogRank>
}