package dev.tilera.kotlin

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption
import java.net.URI

class AllopenGradlePlugin: KotlinCompilerPluginSupportPlugin {
    override fun apply(target: Project) {
        target.repositories.maven{ it.url = URI("https://maven.tilera.xyz") }
        super.apply(target)
    }
    override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
        val project = kotlinCompilation.target.project
        return project.provider {
            emptyList()
        }
    }

    override fun getCompilerPluginId(): String = BuildConfig.KOTLIN_PLUGIN_ID
    override fun getPluginArtifact() = SubpluginArtifact(
        groupId = BuildConfig.KOTLIN_PLUGIN_GROUP,
        artifactId = BuildConfig.KOTLIN_PLUGIN_NAME,
        version = BuildConfig.KOTLIN_PLUGIN_VERSION
    )
    override fun isApplicable(kotlinCompilation: KotlinCompilation<*>) = true
}