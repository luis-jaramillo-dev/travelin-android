import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.projectlab.travelin_android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
//    compileOnly(libs.room.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.travelinandroid.android.application.asProvider().get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = libs.plugins.travelinandroid.android.application.compose.get().pluginId
            implementationClass = "AndroidComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = libs.plugins.travelinandroid.android.library.asProvider().get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = libs.plugins.travelinandroid.android.library.compose.get().pluginId
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("jvmLibrary") {
            id = libs.plugins.travelinandroid.jvm.library.get().pluginId

            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("hilt") {
            id = libs.plugins.travelinandroid.android.hilt.get().pluginId
            implementationClass = "HiltConventionPlugin"
        }

        register("jacocoLibrary") {
            id = libs.plugins.travelinandroid.android.library.jacoco.get().pluginId
            implementationClass = "JacocoAndroidLibraryConventionPlugin"
        }
    }
}
