package com.searchblog.api.domain

class Search(
  val meta: Meta,

  val documents: List<Document>,
) {

  class Meta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean,
  )

  class Document(
    val title: String,
    val url: String,
    val contents: String,
    val blogName: String,
    val datetime: String,
  )

  companion object {
    fun empty() = Search(
      meta = Meta(
        totalCount = 0,
        pageableCount = 1,
        isEnd = true
      ),
      documents = emptyList(),
    )
  }
}