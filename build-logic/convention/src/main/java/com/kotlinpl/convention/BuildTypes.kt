package com.kotlinpl.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    with(commonExtension) {
        when(extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugType(commonExtension)
                        }
                        release {
                            configureReleaseType(commonExtension)
                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugType(commonExtension)
                        }
                        release {
                            configureReleaseType(commonExtension)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Configure release type building.
 */
private fun BuildType.configureReleaseType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}

/**
 * Configure debut building
 */
private fun BuildType.configureDebugType(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    proguardFiles(
        "proguard-rules.pro"
    )
}