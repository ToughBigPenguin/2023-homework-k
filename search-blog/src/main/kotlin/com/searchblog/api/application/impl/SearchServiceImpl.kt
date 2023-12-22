package com.searchblog.api.application.impl

import com.searchblog.api.application.SearchService
import com.searchblog.api.domain.Search
import com.searchblog.api.infrastructure.SearchPort
import com.searchblog.api.interfaces.SearchRequest
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
  private val searchPort: SearchPort
) : SearchService {
  override fun search(searchRequest: SearchRequest): Search = runBlocking{
    searchPort.getKakaoSearch(
      query = searchRequest.query,
      sort = searchRequest.sort,
      page = searchRequest.page,
      size = searchRequest.size,
    ) ?: Search.empty()
  }
}