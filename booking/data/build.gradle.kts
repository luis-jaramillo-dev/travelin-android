plugins {
    alias(libs.plugins.travelinandroid.android.library)

    // Dagger Hilt
    alias(libs.plugins.travelinandroid.android.hilt)
}

android {
    namespace = "com.projectlab.booking.data"
}

dependencies {

    // Core System
    implementation(projects.core.data)
    implementation(projects.core.domain)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)


    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

}