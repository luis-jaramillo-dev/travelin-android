package com.kotlinpl.convention

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

fun Project.configureJacocoWithBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    extensionType: ExtensionType
) {
    apply(plugin = "jacoco")
    commonExtension.apply {
        configure<JacocoPluginExtension> {
            toolVersion = libs.version("jacoco")
        }



        when(extensionType) {
            ExtensionType.APPLICATION -> {
                configureJacocoAndroid()
            }
            ExtensionType.LIBRARY -> {
                configureJacocoAndroid()
            }
        }
    }
}

internal fun Project.configureJacocoJvm() {
    extensions.configure<JavaPluginExtension> {
        tasks.register<JacocoReport>("jacocoTestReport") {
            dependsOn(tasks.withType<Test>())

            val sourceDirs = this.

            reports {
                xml.required.set(true)
                html.required.set(true)

                xml.outputLocation.set(layout.buildDirectory.file(
                    "reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
                ))
                html.outputLocation.set(layout.buildDirectory.dir(
                    "reports/jacoco/jacocoTestReport/html"
                ))
            }
        }

        tasks.withType<Test> {
            useJUnitPlatform()
            finalizedBy("jacocoTestReport")
        }
    }
}

internal fun Project.configureJacocoAndroid() {
    extensions.configure<LibraryExtension> {
        buildTypes {
            getByName("debug") {
                enableUnitTestCoverage = true
            }
        }

        tasks.register<JacocoReport>("jacocoTestReport") {
            dependsOn("testDebugUnitTest")
            reports {
                xml.required.set(true)
                html.required.set(true)
            }
        }
    }
}