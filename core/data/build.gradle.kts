plugins {
    alias(libs.plugins.travelinandroid.android.library)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    // Ksp
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.projectlab.core.data"
}

dependencies {

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Firebase -> Firestore
    implementation(platform(libs.firebase))
    implementation(libs.firebase.firestore)

    // Core System
    implementation(projects.core.domain)
    implementation(projects.core.database)
}