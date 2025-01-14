plugins {
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply  false
    id("com.vanniktech.maven.publish") version "0.29.0"
}