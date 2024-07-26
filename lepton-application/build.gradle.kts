plugins {
    kotlin("jvm") version "1.9.22"
}

group = "com.leuan.lepton"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lepton-module-customer"))
    implementation(project(":lepton-module-framework"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}