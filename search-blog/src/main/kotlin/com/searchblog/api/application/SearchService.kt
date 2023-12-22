package com.searchblog.api.application

import com.searchblog.api.domain.Search
import com.searchblog.api.interfaces.dto.SearchBlogRequest

interface SearchService {
  fun search(searchBlogRequest: SearchBlogRequest): Search
}