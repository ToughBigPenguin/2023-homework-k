package com.searchblog.infrastructure.db.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@Entity
@Table(name = "search_blog_history")
class SearchBlogHistoryEntity constructor(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  val keyword: String,

  @Column(name = "modified_at")
  @LastModifiedDate
  var modifiedAt: LocalDateTime? = null,

  @Column(name = "created_at")
  @CreatedDate
  var createdAt: LocalDateTime? = null,
)