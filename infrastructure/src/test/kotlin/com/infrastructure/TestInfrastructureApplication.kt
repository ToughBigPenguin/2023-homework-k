package com.infrastructure

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestInfrastructureApplication

fun main(args: Array<String>) {
  fromApplication<InfrastructureApplication>().with(TestInfrastructureApplication::class).run(*args)
}
