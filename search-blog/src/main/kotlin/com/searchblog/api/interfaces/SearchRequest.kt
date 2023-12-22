package com.searchblog.api.interfaces

class SearchRequest(
  val query: String,

  val sort: String,

  val page: Int,

  val size: Int,
)