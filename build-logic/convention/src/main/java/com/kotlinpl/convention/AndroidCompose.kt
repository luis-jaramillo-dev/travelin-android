package com.kotlinpl.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.plugins
import org.gradle.plugin.use.PluginDependenciesSpec

/**
 * Configure compose options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            // need a way to fetch version from [libs.versions.toml]
            kotlinCompilerExtensionVersion = libs.version("composeCompiler")
        }

        dependencies {
            val bom = libs.getLibrary("androidx-compose-bom")

            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "debugImplementation"(libs.getLibrary("androidx-compose-ui-tooling"))
            "implementation"(libs.getLibrary("androidx-compose-ui-tooling-preview"))
        }
    }
}
