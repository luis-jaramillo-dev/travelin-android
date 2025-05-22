plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
    alias(libs.plugins.travelinandroid.android.hilt)
}

android {
    namespace = "com.projectlab.feature.onboarding.presentation"

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
    }
}

dependencies {
    // Core System
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)

    // Navigation
    // TODO this should be deleted, or used on :app module
    implementation(libs.hilt.navigation)

    // Testing
    testImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(kotlin("test")) // TODO: Fix format. Add to libs
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Debug Dependencies
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
