package dev.tilera.kotlin

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension

@AutoService(ComponentRegistrar::class)
class AllopenComponentRegistrar: ComponentRegistrar {
    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        DeclarationAttributeAltererExtension.registerExtension(project, AllopenExtension())
    }
}
