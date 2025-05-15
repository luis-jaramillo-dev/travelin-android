plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)

    // Ksp
    alias(libs.plugins.devtools.ksp)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    // Compose
    alias(libs.plugins.compose.compiler)

    // Firebase
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.projectlab.travelin_android"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.projectlab.travelin_android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)
    implementation(projects.auth.presentation)
    implementation(projects.auth.data)
    implementation(projects.feature.onboarding.presentation)
    implementation(projects.navigation)
    implementation(projects.booking.presentation)

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation)


    // Google
    implementation(platform(libs.firebase))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.google.fonts)
    implementation(libs.androidx.runtime.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.activity.compose)

    // UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.material)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
}