plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "lepton"
include("lepton-application")
include("lepton-module-customer")
include("lepton-module-framework")
