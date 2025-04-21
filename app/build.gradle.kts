plugins {
//    alias(libs.plugins.travelinandroid.android.application)
    alias(libs.plugins.travelinandroid.android.application.compose)
}

android {
    namespace = "com.projectlab.travelin_android"

    defaultConfig {
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.google.fonts)

    // core system
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)

    // Auth Module
    implementation(projects.auth.presentation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}