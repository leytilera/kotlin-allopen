import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.gmazzo.buildconfig")
    id("maven-publish")
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable")

    kapt("com.google.auto.service:auto-service:1.0-rc7")
    compileOnly("com.google.auto.service:auto-service-annotations:1.0-rc7")
}

buildConfig {
    packageName(group.toString())
    buildConfigField("String", "KOTLIN_PLUGIN_ID", "\"${rootProject.extra["kotlin_plugin_id"]}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_GROUP", "\"${project.group}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_NAME", "\"${project.name}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${project.version}\"")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = project.name
            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri("https://data.tilera.xyz/maven/")
            credentials {
                username = findProperty("mvnUsername") as String?
                password = findProperty("mvnPassword") as String?
            }
        }
    }
}
