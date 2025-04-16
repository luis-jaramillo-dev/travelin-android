import org.gradle.api.*

plugins {
    alias(libs.plugins.android.library)    // Usamos el plugin Android Library, ya definido en catalogo.
    alias(libs.plugins.kotlin.android)     // Usamos el plugin Kotlin para Android.
    alias(libs.plugins.kotlin.compose)     // Plugin para Compose.
}

android {
    namespace = "com.projectlab.feature.onboarding.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true // Habilitamos el uso de Compose.
    }

    composeOptions {
        // Usamos la version de Kotlin definida en el catalogo:
        // Se podria usar la de compose¿?
        kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
    }

    compileOptions {
        // Config Compatibilidad con Java 11:
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    testImplementation(composeBom)
    androidTestImplementation(composeBom)

    // Dependencias básicas de Compose para el runtime y UI.
    implementation(libs.androidx.runtime.android)
    // Dependencias de Compose:
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    // Dependencia de Material Design:
    implementation(libs.material)
    // Dependencias de Compose para Material3:
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
