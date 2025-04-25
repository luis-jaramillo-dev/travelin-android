package com.kotlinpl.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

/**
 * Configure Kotlin with Android Options
 */

internal fun Project.configureKotlinAndroid(
   commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = libs.version("projectCompileSdk").toInt()

        defaultConfig {
            minSdk = libs.version("projectMinSdk").toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}

