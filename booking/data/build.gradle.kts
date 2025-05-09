plugins {
    alias(libs.plugins.travelinandroid.android.library)
}

android {
    namespace = "com.projectlab.booking.data"
}

dependencies {
    // Core
    implementation(projects.core.domain)
    implementation(projects.core.data)
}