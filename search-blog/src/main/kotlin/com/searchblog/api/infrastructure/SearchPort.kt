package com.searchblog.api.infrastructure

import com.searchblog.api.domain.Search

interface SearchPort {

  suspend fun getKakaoSearch(
    query: String,
    sort: String,
    page: Int,
    size: Int,
  ): Search?

}