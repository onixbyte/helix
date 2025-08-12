plugins {
    id("java")
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

val artefactVersion: String by project

group = "com.onixbyte.onixboot"
version = artefactVersion

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    maven("https://repo.nju.edu.cn/maven/")
    maven("https://maven.proxy.ustclug.org/maven2/")
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    implementation(libs.jwt.core)
    implementation(libs.spring.boot.configurationProcessor)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.webFlux)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.redis)
    implementation(libs.spring.boot.starter.cache)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.mybatis)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
    testImplementation(libs.spring.security.test)
    testImplementation(libs.spring.boot.starter.mybatisTest)
    runtimeOnly(libs.postgres.driver)
    testRuntimeOnly(libs.junit.launcher)
}

tasks.test {
    useJUnitPlatform()
}
