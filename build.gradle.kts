
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories { gradlePluginPortal() }

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.karim"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	api("org.springframework.boot:spring-boot-starter-jooq")
	api("org.jooq:jooq")
	api("org.postgresql:r2dbc-postgresql")
	api("io.r2dbc:r2dbc-pool")
	api("io.r2dbc:r2dbc-proxy")
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation ("org.flywaydb:flyway-core")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")
	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	runtimeOnly ("org.postgresql:postgresql")
	testImplementation ("org.springframework.boot:spring-boot-starter-test")
	testImplementation ("io.projectreactor:reactor-test")
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