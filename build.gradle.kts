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
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}
allprojects {
    repositories {
        mavenCentral()
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.3.2")
    }
}

allprojects {
    dependencies {
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")
        apply(plugin = "kotlin")
        apply(plugin = "kotlin-spring")
        apply(plugin = "kotlin-kapt")
        apply(plugin = "kotlin-noarg")

        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // WEB
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security:3.3.2")

        // DB 相关
        implementation("org.postgresql:postgresql:42.7.3")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
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
}
subprojects {
    tasks.bootJar {
        enabled = false
    }
}
tasks.bootJar {
    enabled = false
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}