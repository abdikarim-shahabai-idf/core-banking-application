import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
	gradlePluginPortal()
	mavenCentral()
}

group = "com.karim"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"

	kotlin("kapt") version "1.4.32"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

dependencies{
	implementation ("org.springframework.boot:spring-boot-starter")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")
	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation ("org.springframework.data:spring-data-r2dbc:3.1.4")
	implementation ("io.projectreactor:reactor-core")
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation ("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation ("org.postgresql:r2dbc-postgresql")
	implementation ("io.r2dbc:r2dbc-pool")
	implementation ("io.r2dbc:r2dbc-proxy")
	implementation ("org.flywaydb:flyway-core")
	implementation ("org.mapstruct:mapstruct:1.5.3.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
	implementation (project(":core-library"))
	implementation (project(":model"))
	runtimeOnly ("org.postgresql:postgresql")
	compileOnly ("org.projectlombok:lombok:1.18.24")
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
