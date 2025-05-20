plugins {
    alias(libs.plugins.travelinandroid.jvm.library)

    // Ksp
    alias(libs.plugins.travelinandroid.android.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}