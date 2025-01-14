
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.google.devtools.ksp") version "2.0.20-1.0.25"
    kotlin("plugin.allopen") version "2.0.20"
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

kotlin {

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




