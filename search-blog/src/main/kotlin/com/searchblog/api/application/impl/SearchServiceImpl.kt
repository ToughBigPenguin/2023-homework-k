package com.searchblog.api.application.impl

import com.searchblog.api.application.SearchService
import com.searchblog.api.domain.Search
import com.searchblog.api.domain.SearchBlogRank
import com.searchblog.api.infrastructure.SearchPort
import com.searchblog.api.interfaces.dto.SearchBlogRequest
import com.searchblog.infrastructure.db.entity.SearchBlogHistoryEntity
import com.searchblog.infrastructure.db.SearchBlogHistoryRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchServiceImpl(
  private val searchPort: SearchPort,
  private val searchBlogHistoryRepository: SearchBlogHistoryRepository,
) : SearchService {

  @Transactional
  override fun search(searchBlogRequest: SearchBlogRequest): Search = runBlocking {
    saveSearchBlogKeyword(search = searchBlogRequest.query)

    searchPort.getKakaoSearch(
      query = searchBlogRequest.query,
      sort = searchBlogRequest.sort,
      page = searchBlogRequest.page,
      size = searchBlogRequest.size,
    ) ?: Search.empty()
  }

  override fun saveSearchBlogKeyword(search: String) {
    searchBlogHistoryRepository.save(
      SearchBlogHistoryEntity(
        keyword = search
      )
    )
  }

  override fun getSearchBlogKeywordRank(): List<SearchBlogRank> {
    return searchBlogHistoryRepository.findGroupByKeywordTop10().map {
      SearchBlogRank(
        keyword = it.keyword,
        count = it.count,
      )
    }
  }
}