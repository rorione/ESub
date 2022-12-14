@file:Suppress("PropertyName")

val kotlin_version: String by project
val logback_version: String by project
val web3j_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    id("org.web3j") version "4.9.4"
}

group = "io.github.rorione"
version = "0.0.1"
application {
    mainClass.set("io.github.rorione.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://hyperledger.jfrog.io/hyperledger/besu-maven")
        content { includeGroupByRegex("org\\.hyperledger\\..*") }
    }
    maven {
        url = uri("https://artifacts.consensys.net/public/maven/maven/")
        content { includeGroupByRegex("tech\\.pegasys\\..*") }
    }
    maven {
        url = uri("https://dl.cloudsmith.io/public/consensys/quorum-mainnet-launcher/maven/")
        content { includeGroupByRegex("net\\.consensys\\..*") }
    }
    maven {
        url = uri("https://splunk.jfrog.io/splunk/ext-releases-local")
        content { includeGroupByRegex("com\\.splunk\\..*") }
    }
    @Suppress("DEPRECATION")
    jcenter()
}

dependencies {
    // Logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Web3j
    implementation("org.web3j:core:$web3j_version")

    // Tests
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlin_version")
    testImplementation("org.web3j:web3j-unit:$web3j_version")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
