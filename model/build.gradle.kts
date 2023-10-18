import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
  gradlePluginPortal()
  mavenCentral()
}

plugins {
  kotlin("jvm") version "1.8.22"
  kotlin("plugin.spring") version "1.8.22"
}
dependencies{
  implementation ("org.springframework.data:spring-data-r2dbc:3.1.4")
  compileOnly("org.projectlombok:lombok:1.18.24")
}