import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("com.google.devtools.ksp") version "2.0.20-1.0.25"
    kotlin("plugin.allopen") version "2.0.20"
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
        }
    }
    js(IR) {
        browser()
        binaries.executable()
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        unitTestVariant {
            dependencies {
                testImplementation(libs.mockative)
                testImplementation(kotlin("test-junit"))
                testImplementation(libs.junit)
                testImplementation(libs.kotlinx.coroutines.test)
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here


        }
        iosMain.dependencies {
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.0.0-RC2"))
            implementation("io.insert-koin:koin-core")
            implementation("io.insert-koin:koin-test")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        }
        androidMain.dependencies {
            implementation (libs.stripe.android)
            implementation(libs.jetbrains.kotlinx.coroutines.core)
            implementation(libs.gson)
        }
        iosTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.mockative)
        }
        jsMain.dependencies {
            implementation(projects.striperest)
        }
    }
    task("testClasses")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }
}


dependencies {
    configurations
        .filter { it.name.startsWith("ksp") && it.name.contains("Test") }
        .forEach {
            add(it.name, "io.mockative:mockative-processor:2.2.2")
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
    dependencies {
        implementation(libs.androidx.activity.compose)
    }
}
val taskIsRunningTest = gradle.startParameter.taskNames
    .any { it == "check" || it.startsWith("test") || it.contains("Test") }

if (taskIsRunningTest) {
    allOpen {
        annotation("Mockable")
    }
}

ksp {
    arg("io.mockative:mockative:opt-in:io.github.OptInType", "kotlinx.cinterop.ExperimentalForeignApi")
    arg("io.mockative:mockative:opt-in:io.github.*", "kotlin.ExperimentalStdlibApi")
    arg("io.mockative:mockative:opt-in", "kotlin.ExperimentalUnsignedTypes")
}
