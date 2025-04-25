import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include

plugins {
//    alias(libs.plugins.travelinandroid.android.application)
    alias(libs.plugins.travelinandroid.android.application.compose)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlin.serialization)
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
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.google.fonts)
    implementation(libs.kotlinx.serialization.json)

    // core system
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)

    implementation(projects.navigation)
    // onboarding
    implementation(projects.feature.onboarding.presentation) // TODO check why this is giving issues
    //implementation(project(":navigation"))
    implementation(project(":feature:onboarding:presentation")) // TODO this must not be used in this way

    testImplementation(libs.junit)
    // TODO check why these are giving issues
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
