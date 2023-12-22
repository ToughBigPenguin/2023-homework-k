package com.searchblog.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
  scanBasePackages = [
    "com.searchblog"
  ]
)
class SearchBlogApplication

fun main(args: Array<String>) {
  runApplication<SearchBlogApplication>(*args)
}
