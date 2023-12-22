package com.searchblog.infrastructure.db

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "search_blog_history")
data class SearchBlogHistoryEntity constructor(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  val keyword: String,

  @Column(name = "modified_at")
  val modifiedAt: LocalDateTime,

  @Column(name = "created_at")
  val createdAt: LocalDateTime
)