plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.leuan.lepton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lepton-module-framework"))
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}