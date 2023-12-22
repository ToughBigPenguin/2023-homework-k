package com.searchblog.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@SpringBootApplication(
  scanBasePackages = [
    "com.searchblog",
  ]
)
@EntityScan(
  value = [
    "com.searchblog.infrastructure.db"
  ]
)
@EnableJpaRepositories(
  value = [
    "com.searchblog.infrastructure.db"
  ]
)
class SearchBlogApplication

fun main(args: Array<String>) {
  runApplication<SearchBlogApplication>(*args)
}
