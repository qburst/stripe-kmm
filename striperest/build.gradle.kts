plugins {
    kotlin("multiplatform")

}
repositories {
    mavenCentral() // Ensure Maven Central is included
}
kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
            }
        }
        binaries.executable()
    }

    sourceSets {
       commonMain.dependencies {
           implementation(libs.ktor.client.core)
           implementation(libs.ktor.client.js)
           implementation(libs.ktor.client.content.negotiation)
           implementation(libs.ktor.serialization.kotlinx.json)
       }

        jsMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.js)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

        }

    }
}
