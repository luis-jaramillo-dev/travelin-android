// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // Ksp
    alias(libs.plugins.devtools.ksp) apply false

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android) apply false

    // Compose
    alias(libs.plugins.compose.compiler) apply false

    // Firebase
    alias(libs.plugins.google.services) apply false

    // Proto DataStore
    alias(libs.plugins.protobuf) apply false
}
