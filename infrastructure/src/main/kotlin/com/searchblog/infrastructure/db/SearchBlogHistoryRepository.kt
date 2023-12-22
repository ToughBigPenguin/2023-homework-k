package com.searchblog.infrastructure.db

import org.springframework.data.jpa.repository.JpaRepository

interface SearchBlogHistoryRepository : JpaRepository<SearchBlogHistoryEntity, Long>