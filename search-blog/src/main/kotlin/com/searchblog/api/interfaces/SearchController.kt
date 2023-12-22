package com.searchblog.api.interfaces

import com.searchblog.api.application.SearchService
import com.searchblog.api.global.dto.ApiBaseResponse
import com.searchblog.api.interfaces.dto.SearchBlogRankResponse
import com.searchblog.api.interfaces.dto.SearchBlogRequest
import com.searchblog.api.interfaces.dto.SearchBlogResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
  private val searchService: SearchService,
) {

  @GetMapping("/search/blog")
  fun searchBlog(
    // TODO : 벨리데이션 ERROR 헨들러 추가하기
    searchBlogRequest: SearchBlogRequest,
  ): ApiBaseResponse<SearchBlogResponse> {

    return ApiBaseResponse(
      data = SearchBlogResponse.of(
        searchService.search(searchBlogRequest = searchBlogRequest)
      )
    )
  }

  @GetMapping("/search/blog-rank")
  fun searchBlogKeywordRank(): ApiBaseResponse<List<SearchBlogRankResponse>> {

    return ApiBaseResponse(
      data = SearchBlogRankResponse.of(
        searchService.getSearchBlogKeywordRank()
      )
    )
  }
}