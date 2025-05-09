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

        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()

            "implementation"(platform(bom))
            "implementation"(libs.findLibrary("androidx-ui").get())
            "implementation"(libs.findLibrary("androidx-ui-graphics").get())
            "implementation"(libs.findLibrary("androidx-ui-tooling").get())
            "debugImplementation"(libs.findLibrary("androidx-ui-tooling-preview").get())
            "androidTestImplementation"(platform(bom))
        }
    }
}
