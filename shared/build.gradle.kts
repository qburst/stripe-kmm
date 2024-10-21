import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
  // alias(libs.plugins.sqldelight)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
            webpackTask {
                mainOutputFileName = "index.js"
            }
        }
        binaries.executable()
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets {
        commonMain.dependencies {
           // implementation(libs.kotlinx.coroutines.core)
          //  implementation(libs.ktor.client.core)
         //   implementation(libs.ktor.client.content.negotiation)
        //    implementation(libs.ktor.serialization.kotlinx.json)
         //   implementation(libs.runtime)
        //    implementation(libs.kotlinx.datetime)
         //   implementation(libs.koin.core)
        }
        androidMain.dependencies {
            implementation (libs.stripe.android.v20482)
            implementation(libs.ktor.client.android)
            implementation(libs.android.driver)
            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)
        }
        wasmJsMain.dependencies {
            implementation(npm("@stripe/stripe-js", "^4.4.0"))
//            implementation("io.ktor:ktor-client-core:2.0.0")
//            implementation("io.ktor:ktor-client-js:2.0.0")
//            implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
//            implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")

        }
    }
}

android {
    namespace = "com.qburst.stripe_kmm.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
dependencies {
    implementation(libs.androidx.activity.ktx)
}
