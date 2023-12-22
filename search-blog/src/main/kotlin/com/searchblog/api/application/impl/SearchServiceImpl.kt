package com.searchblog.api.application.impl

import com.searchblog.api.application.SearchService
import com.searchblog.api.domain.Search
import com.searchblog.api.infrastructure.SearchPort
import com.searchblog.api.interfaces.dto.SearchBlogRequest
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
  private val searchPort: SearchPort
) : SearchService {
  override fun search(searchBlogRequest: SearchBlogRequest): Search = runBlocking{
    searchPort.getKakaoSearch(
      query = searchBlogRequest.query,
      sort = searchBlogRequest.sort,
      page = searchBlogRequest.page,
      size = searchBlogRequest.size,
    ) ?: Search.empty()
  }
}