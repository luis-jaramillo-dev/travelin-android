plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
}

android {
    namespace = "com.projectlab.core.presentation.designsystem"
}

dependencies {

    // Core System
    implementation(projects.core.data)
    implementation(projects.core.domain)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // UI
//    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.compose.google.fonts)
    implementation(libs.androidx.compose.material.icons)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.runtime.android)
}