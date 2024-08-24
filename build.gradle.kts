plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("io.freefair.lombok") version "8.1.0"

    kotlin("jvm") version "1.9.22"
    kotlin("kapt") version "2.0.0"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.lombok") version "2.0.0"
    kotlin("plugin.allopen") version "2.0.0"
    kotlin("plugin.noarg") version "2.0.0"
}

group = "com.leuan"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // WEB
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    // DB 相关
    implementation("org.postgresql:postgresql")
    implementation("mysql:mysql-connector-java")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")
    implementation(group = "com.querydsl", name = "querydsl-jpa", version = "5.1.0", classifier = "jakarta")
    kapt(group = "com.querydsl", name = "querydsl-apt", version = "5.1.0", classifier = "jakarta")

    // 缓存
    implementation("org.redisson:redisson-spring-boot-starter:3.33.0")

    // 工具相关
    implementation("org.projectlombok:lombok")
    implementation("cn.hutool:hutool-all:5.8.29")
    allOpen {
        annotations("jakarta.persistence.Entity")
        annotations("com.leuan.lepton.common.annotations.AllOpen")
        annotations("lombok.NoArgsConstructor")
    }
    noArg {
        annotations("jakarta.persistence.Entity")
        annotations("com.leuan.lepton.common.annotations.NoArg")
        annotations("lombok.NoArgsConstructor")
    }

    // AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // POJO 转换
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // 文档
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")


    // 测试相关
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
