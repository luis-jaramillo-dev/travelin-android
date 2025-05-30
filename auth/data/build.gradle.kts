plugins {
    alias(libs.plugins.travelinandroid.android.library)
    alias(libs.plugins.travelinandroid.android.hilt)
    alias(libs.plugins.travelinandroid.android.library.jacoco)
}

android {
    namespace = "com.projectlab.auth.data"
}

dependencies {

    // Core System
    implementation(projects.core.domain)
    implementation(projects.core.data)

    // Auth
    implementation(projects.auth.domain)
}
