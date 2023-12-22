package com.searchblog.infrastructure.api.dto

import com.fasterxml.jackson.annotation.JsonProperty

class KakaoBlogResponse(
  val meta: Meta,

  val documents: List<Document>,
) {

  class Meta(
    @JsonProperty("total_count")
    val totalCount: Int,

    @JsonProperty("pageable_count")
    val pageableCount: Int,

    @JsonProperty("is_end")
    val isEnd: Boolean,
  )

  class Document(
    val title: String,
    val url: String,
    val contents: String,
    val blogname: String,
    val thumbnail: String,
    val datetime: String,
  )
}
