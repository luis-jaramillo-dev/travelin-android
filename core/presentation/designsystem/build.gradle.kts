plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
    alias(libs.plugins.travelinandroid.android.library.jacoco)
}

android {
    namespace = "com.projectlab.core.presentation.designsystem"
}

dependencies {

    // Core System
    // TODO: check which core.data dependencies are used
    // in this module, and create an abstraction
    implementation(projects.core.data)
    implementation(projects.core.domain)

    implementation(libs.compose.google.fonts)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.runtime.android)
}