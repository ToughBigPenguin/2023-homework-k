package com.searchblog.api.interfaces

import com.searchblog.api.domain.Search

class SearchResponse(
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
    val thumbnail: String,
    val datetime: String,
  )

  companion object {
    fun of(search: Search): SearchResponse {
      return SearchResponse(
        meta = Meta(
          totalCount = search.meta.totalCount,
          pageableCount = search.meta.pageableCount,
          isEnd = search.meta.isEnd,
        ),
        documents = search.documents.map {
          Document(
            title = it.title,
            url = it.url,
            contents = it.contents,
            blogName = it.blogName,
            thumbnail = it.thumbnail,
            datetime = it.datetime,
          )
        }
      )
    }
  }
}