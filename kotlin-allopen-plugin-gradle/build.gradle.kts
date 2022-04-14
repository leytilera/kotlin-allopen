import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-gradle-plugin")
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig")
    id("maven-publish")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
}

buildConfig {
    val project = project(":kotlin-allopen-plugin")
    packageName(project.group.toString())
    buildConfigField("String", "KOTLIN_PLUGIN_ID", "\"${rootProject.extra["kotlin_plugin_id"]}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_GROUP", "\"${project.group}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_NAME", "\"${project.name}\"")
    buildConfigField("String", "KOTLIN_PLUGIN_VERSION", "\"${project.version}\"")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

gradlePlugin {
    plugins {
        create("kotlinAllopenPlugin") {
            id = "dev.tilera.kotlin.plugin.allopen"
            displayName = "Kotlin All Open compiler plugin"
            implementationClass = "dev.tilera.kotlin.AllopenGradlePlugin"
        }
    }
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
