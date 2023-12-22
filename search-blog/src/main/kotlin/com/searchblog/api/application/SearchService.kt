package com.searchblog.api.application

import com.searchblog.api.domain.Search
import com.searchblog.api.interfaces.dto.SearchRequest

interface SearchService {
  fun search(searchRequest: SearchRequest): Search
}