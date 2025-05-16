plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    // Ksp
    alias(libs.plugins.devtools.ksp)
    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)
    // Compose
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.projectlab.booking.presentation"
    compileSdk = 35

    defaultConfig {
        minSdk = 25

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
    implementation(projects.core.domain)
    implementation(projects.core.data)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    implementation(libs.androidx.foundation.android)
    implementation(project(":booking:domain"))
    implementation (project(":core:presentation:designsystem"))
    implementation(libs.androidx.compose.ui.ui.tooling.preview.android)
    implementation(libs.hilt.navigation)
    debugImplementation(libs.androidx.ui.tooling)
    ksp(libs.hilt.android.compiler)

    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.runtime)
    // UI
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.ui.tooling)
}