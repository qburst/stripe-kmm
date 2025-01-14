import com.vanniktech.maven.publish.SonatypeHost
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "io.github.abdulbasithqb"
version = "1.0.1"


kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
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

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.github.abdulbasithqb.stripekmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 30
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        implementation(libs.androidx.activity.compose)
    }
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "stripekmm", version.toString())

    pom {
        name = "Stripe KMM library"
        description = "A stripe kmm library."
        inceptionYear = "2024"
        url = "https://github.com/qburst/stripe-kmm/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "qburst"
                name = "Qburst Open source community"
                url = "https://github.com/qburst/"
            }
        }
        scm {
            url = "https://github.com/qburst/stripe-kmm/"
            connection = "scm:git:git://github.com/qburst/stripe-kmm.git"
            developerConnection = "scm:git:ssh://git@github.com/qburst/stripe-kmm.git"
        }
    }
}


