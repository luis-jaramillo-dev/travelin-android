plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // Compose
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.projectlab.core.presentation.designsystem"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Core System
    implementation(projects.core.data)
    implementation(projects.core.domain)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.google.fonts)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.runtime.android)

    // Testing
    debugImplementation(libs.androidx.compose.ui.tooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}