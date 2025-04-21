plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
}

android {
    namespace = "com.projectlab.core.presentation.designsystem"

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(libs.androidx.compose.material3)
    implementation(libs.compose.google.fonts)
}