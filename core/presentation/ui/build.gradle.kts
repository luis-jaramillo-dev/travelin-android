plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
    alias(libs.plugins.travelinandroid.android.hilt)

}

android {
    namespace = "com.projectlab.core.presentation.ui"
}

dependencies {

    // Core System
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.domain)

    // Maps
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.kotlinx.coroutines.play.services)

    // Compose
    implementation(libs.androidx.material3.android)
    implementation(libs.coil.compose)
    api(libs.androidx.compose.material3)
}