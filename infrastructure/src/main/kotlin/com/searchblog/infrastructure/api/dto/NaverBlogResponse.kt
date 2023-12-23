package com.searchblog.infrastructure.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

class NaverBlogResponse(
  val lastBuildDate: String,

  val total: Long,

  val start: Long,

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
