plugins {
    alias(libs.plugins.ktor)
    application
}

group = "com.qburst.stripe_kmm"
version = "1.0.0"
application {
    mainClass.set("com.qburst.stripe_kmm.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["io.ktor.development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
   // testImplementation(libs.ktor.server.tests)
    //testImplementation(libs.kotlin.test.junit)
}