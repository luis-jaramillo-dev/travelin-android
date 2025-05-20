plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
    alias(libs.plugins.travelinandroid.android.hilt)
}

android {
    namespace = "com.projectlab.auth.presentation"
}

dependencies {

    // Core System
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)

    // Navigation
    // TODO this should be deleted, or used on :app module
    implementation(libs.hilt.navigation)

    // Material Icons
    implementation(libs.androidx.compose.material.icons)
}