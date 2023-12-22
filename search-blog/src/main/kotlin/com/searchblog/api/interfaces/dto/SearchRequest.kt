package com.searchblog.api.interfaces.dto

class SearchRequest(
  val query: String,

  val sort: String,

  val page: Int,

  val size: Int,
)