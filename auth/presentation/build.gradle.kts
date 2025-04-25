plugins {
    alias(libs.plugins.travelinandroid.android.library.compose)
    alias(libs.plugins.compose)
}

android {
    namespace = "com.projectlab.auth.presentation"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}