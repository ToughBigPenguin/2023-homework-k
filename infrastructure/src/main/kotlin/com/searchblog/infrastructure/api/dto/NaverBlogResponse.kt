package com.searchblog.infrastructure.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

class NaverBlogResponse(
  val lastBuildDate: String,

  val total: Int,

  val start: Int,

  val display: Long,

  val items: List<Item>
) {
  class Item(
    val title: String,
    val link: String,
    val description: String,
    @JsonProperty("bloggername")
    val bloggerName: String,
    @JsonProperty("bloggerlink")
    val bloggerLink: String,
    val postdate: String,
  )
}
