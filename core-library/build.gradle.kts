import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
  kotlin("jvm") version "1.8.22"
}

repositories {
  gradlePluginPortal()
  mavenCentral()
}


java {
  sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation ("org.springframework.data:spring-data-r2dbc:3.1.4")
  implementation ("io.projectreactor:reactor-core")
  implementation ("org.springframework:spring-webflux:6.0.11")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

