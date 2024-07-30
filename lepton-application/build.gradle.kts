plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}
dependencies {
    implementation(project(":lepton-module-customer"))
    implementation(project(":lepton-module-framework"))
}



tasks.bootJar {
    this.archiveBaseName.set("lepton")
    this.enabled = true
    this.mainClass = "com.leuan.lepton.LeptonApplicationKt"
}

tasks.jar {
    enabled = true
}

tasks.test {
    useJUnitPlatform()
}