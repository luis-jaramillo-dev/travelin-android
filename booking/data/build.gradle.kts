plugins {
    alias(libs.plugins.travelinandroid.android.library)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    // KSP
    alias(libs.plugins.devtools.ksp)
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

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)

}