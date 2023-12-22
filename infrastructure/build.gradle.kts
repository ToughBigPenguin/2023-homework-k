
dependencies {
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.2")
  implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

  runtimeOnly("com.h2database:h2")

  testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("io.github.serpro69:kotlin-faker:1.14.0")
}

tasks.named<Jar>("jar") {
  enabled = true
}
