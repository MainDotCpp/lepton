plugins {
    kotlin("jvm")
}

group = "com.leuan.lepton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
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