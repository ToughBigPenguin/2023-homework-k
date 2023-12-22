
dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.7.2")

  runtimeOnly("com.h2database:h2")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")

  api(project(":infrastructure"))
}

tasks.named<Jar>("jar") {
  enabled = true
}
