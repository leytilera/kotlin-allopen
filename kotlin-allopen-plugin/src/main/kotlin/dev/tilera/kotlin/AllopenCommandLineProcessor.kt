package dev.tilera.kotlin

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor

@AutoService(CommandLineProcessor::class)
class AllopenCommandLineProcessor: CommandLineProcessor {

    override val pluginId: String = BuildConfig.KOTLIN_PLUGIN_ID
    override val pluginOptions: Collection<CliOption> = emptyList()

}
