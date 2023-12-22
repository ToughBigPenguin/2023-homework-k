package com.searchblog.api.global.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiBaseResponse<T>(
  val data: T? = null,
  val error: ErrorResponse? = null,
)
