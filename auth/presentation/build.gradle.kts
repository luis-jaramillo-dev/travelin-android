plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
}

android {
    namespace = "com.projectlab.auth.presentation"

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.compose.google.fonts)

    // core dependencies
    implementation(projects.travelinAndroid.core.presentation.designsystem)
    implementation(projects.travelinAndroid.core.presentation.ui)
}