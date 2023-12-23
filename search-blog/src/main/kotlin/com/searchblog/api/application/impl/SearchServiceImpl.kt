package com.searchblog.api.application.impl

import com.searchblog.api.application.SearchService
import com.searchblog.api.domain.Search
import com.searchblog.api.domain.SearchBlogRank
import com.searchblog.api.infrastructure.SearchPort
import com.searchblog.api.interfaces.dto.SearchBlogRequest
import com.searchblog.infrastructure.db.SearchBlogHistoryRepository
import com.searchblog.infrastructure.db.entity.SearchBlogHistoryEntity
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

    // kakao api error 발생시 naver api 호출
    searchPort.getKakaoSearch(
      query = searchBlogRequest.query,
      sort = searchBlogRequest.sort,
      page = searchBlogRequest.page,
      size = searchBlogRequest.size,
    ) ?: searchPort.getNaverSearch(
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

  // TODO : Redis 에 데이터 쌓아서 노출하고, 만약 키 없으면 디비에서 끄내서 노출하도록 변경하기
  override fun getSearchBlogKeywordRank(): List<SearchBlogRank> {
    return searchBlogHistoryRepository.findGroupByKeywordTop10().map {
      SearchBlogRank(
        keyword = it.keyword,
        count = it.count,
      )
    }
  }
}