package com.searchblog.api.infrastructure

import com.searchblog.api.domain.Search
import com.searchblog.infrastructure.api.KakaoApi
import com.searchblog.infrastructure.api.NaverApi
import com.searchblog.infrastructure.api.dto.KakaoBlogResponse
import com.searchblog.infrastructure.api.dto.NaverBlogResponse
import org.springframework.stereotype.Component

@Component
class SearchAdapter(
  private val kakaoApi: KakaoApi,
  private val naverApi: NaverApi,
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

  override suspend fun getNaverSearch(query: String, sort: String, page: Int, size: Int): Search? {
    val response = naverApi.getSearchBlog(
      searchWord = query,
      sort = "sim",
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
  datetime = datetime,
)

fun NaverBlogResponse.toSearch() = Search(
  meta = toMeta(),
  documents = items.map {
    it.toDocument()
  }
)

fun NaverBlogResponse.toMeta() = Search.Meta(
  totalCount = total,
  pageableCount = start,
  isEnd = false,
)

fun NaverBlogResponse.Item.toDocument() = Search.Document(
  title = title,
  url = link,
  contents = description,
  blogName = bloggerName,
  datetime = postdate,
)