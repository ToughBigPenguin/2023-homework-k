package com.searchblog.infrastructure;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication(
  scanBasePackages = ["com.searchblog"]
)
class InfrastructureTestApplication

@ContextConfiguration(
  classes = [
    InfrastructureTestApplication::class,
  ]
)
@SpringBootTest
class InfrastructureApplicationTests