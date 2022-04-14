buildscript {
    extra["kotlin_plugin_id"] = "dev.tilera.kotlin.allopen"
}

plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("org.jetbrains.dokka") version "0.10.0" apply false
    id("com.gradle.plugin-publish") version "0.11.0" apply false
    id("com.github.gmazzo.buildconfig") version "2.0.2" apply false
}

allprojects {
    group = "dev.tilera.kotlin"
    version = "0.1.0"
}

subprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}
