plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    kotlin("plugin.serialization") version "1.9.20"
}
repositories {
    mavenCentral() // Ensure this is included
    maven { url =  uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    google() // Optional, for Android dependencies
}