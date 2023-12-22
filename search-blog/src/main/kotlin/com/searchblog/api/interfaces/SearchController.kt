package com.searchblog.api.interfaces

import com.searchblog.api.application.SearchService
import com.searchblog.api.global.dto.ApiBaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
  private val searchService: SearchService,
) {

  @GetMapping("/search")
  fun search(
    // TODO : 벨리데이션 ERROR 헨들러 추가하기
    searchRequest: SearchRequest,
  ): ApiBaseResponse<SearchResponse> {

    return ApiBaseResponse(
      data = SearchResponse.of(
        searchService.search(searchRequest = searchRequest)
      )
    )
  }
}