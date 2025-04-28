plugins {
//    alias(libs.plugins.travelinandroid.android.application)
    alias(libs.plugins.travelinandroid.android.application.compose)

    // Ksp
    alias(libs.plugins.devtools.ksp)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

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

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.google.fonts)

    // core system
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
}