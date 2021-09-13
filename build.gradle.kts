import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlin = "1.5.21"
    val springBoot = "2.6.0-SNAPSHOT"
    val flyway = "7.14.1"

    kotlin("jvm") version kotlin
    kotlin("plugin.spring") version kotlin
    kotlin("plugin.jpa") version kotlin

    id("org.springframework.boot") version springBoot
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    id("org.flywaydb.flyway") version flyway
}

group = "com.mutoe"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    val springBoot = "2.6.0-SNAPSHOT"
    val flyway = "7.14.1"

    implementation("org.springframework.boot:spring-boot-starter:$springBoot")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBoot")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools:$springBoot")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security:$springBoot")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

    // persistence
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBoot")
    implementation("org.flywaydb:flyway-core:$flyway")
    implementation("org.postgresql:postgresql:42.2.23.jre7")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBoot")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
