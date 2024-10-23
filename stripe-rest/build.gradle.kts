plugins {
    kotlin("multiplatform")
}
repositories {
    mavenCentral() // Ensure Maven Central is included
}
kotlin {
    wasm {
        // WebAssembly target is configured here
        binaries.executable()
        browser {
            commonWebpackConfig {

            }
        }
    }
    sourceSets {
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.js)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
        }

    }
}
