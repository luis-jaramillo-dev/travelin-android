plugins {
    alias(libs.plugins.travelinandroid.android.library)
    alias(libs.plugins.travelinandroid.android.hilt)
    alias(libs.plugins.travelinandroid.android.library.jacoco)
}

android {
    namespace = "com.projectlab.core.database"
}

dependencies {

    // Firebase -> Firestore
    api(platform(libs.firebase))
    api(libs.firebase.firestore)


    // Core System
    implementation(projects.core.domain)
    // implementation(projects.core.data)
}