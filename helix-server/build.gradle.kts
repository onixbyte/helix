plugins {
    id("java")
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

val artefactVersion: String by project

group = "com.onixbyte.helix"
version = artefactVersion

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation(libs.commons.io)
    implementation(platform(libs.aws.sdk.bom))
    implementation(libs.aws.sdk.s3)
    implementation(platform(libs.onixbyte.versionCatalogue))
    implementation(libs.onixbyte.tuple)
    implementation(libs.onixbyte.commonToolbox)
    implementation(libs.onixbyte.identityGenerator)
    implementation(libs.onixbyte.captcha)
    implementation(libs.jwt.core)
    implementation(libs.spring.boot.configurationProcessor)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.webFlux)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.redis)
    implementation(libs.spring.boot.starter.cache)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.mybatis.starter.core)
    implementation(libs.jackson.jsr310)
    implementation(libs.jspecify.core)
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
    testImplementation(libs.spring.security.test)
    testImplementation(libs.mybatis.starter.test)
    runtimeOnly(libs.postgres.driver)
    testRuntimeOnly(libs.h2.database)
    testRuntimeOnly(libs.junit.launcher)
}

tasks.test {
    useJUnitPlatform()
}
