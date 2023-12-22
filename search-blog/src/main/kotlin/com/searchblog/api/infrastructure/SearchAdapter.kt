package com.searchblog.api.infrastructure

import com.searchblog.api.domain.Search
import com.searchblog.infrastructure.api.KakaoApi
import com.searchblog.infrastructure.api.dto.KakaoBlogResponse
import org.springframework.stereotype.Component

@Component
class SearchAdapter(
  private val kakaoApi: KakaoApi,
) : SearchPort {
  override suspend fun getKakaoSearch(query: String, sort: String, page: Int, size: Int): Search? {

    val response = kakaoApi.getSearchBlog(
      searchWord = query,
      sort = sort,
      page = page,
      size = size,
    )

    return response?.toSearch()
  }
}

fun KakaoBlogResponse.toSearch() = Search(
  meta = meta.toMeta(),
  documents = documents.map { it.toDocument() }
)

fun KakaoBlogResponse.Meta.toMeta() = Search.Meta(
  totalCount = totalCount,
  pageableCount = pageableCount,
  isEnd = isEnd,
)

fun KakaoBlogResponse.Document.toDocument() = Search.Document(
  title = title,
  url = url,
  contents = contents,
  blogName = blogname,
  thumbnail = thumbnail,
  datetime = datetime,
)