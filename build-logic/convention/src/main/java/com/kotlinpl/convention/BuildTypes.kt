package com.kotlinpl.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    val apiKey = gradleLocalProperties(
        rootDir,
        providers = providers
    ).getProperty("API_KEY")

    val baseUrl = gradleLocalProperties(
        rootDir,
        providers
    ).getProperty("BASE_URL")

    commonExtension.apply {
        buildFeatures {
            buildConfig = true
        }

        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey, baseUrl)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apiKey, baseUrl)
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apiKey, baseUrl)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apiKey, baseUrl)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Configure base build type options for debug.
 * making available keys and base url on any build type of our app
 */
private fun BuildType.configureDebugBuildType(apiKey: String, baseUrl: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    apiKey: String,
    baseUrl: String
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"

    )
}
