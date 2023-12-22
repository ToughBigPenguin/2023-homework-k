package com.searchblog.api.interfaces.dto

class SearchBlogRequest(
  val query: String,

  val sort: String,

  val page: Int,

  val size: Int,
)