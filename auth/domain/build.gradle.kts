plugins {
    alias(libs.plugins.travelinandroid.android.library)
}

android {
    namespace = "com.projectlab.auth.domain"
}

dependencies {
    // Core System
    implementation(projects.core.domain)

    // Firebase -> FirebaseAuth
    // TODO this shouldn't be here
    // You can move it to :auth:data :auth:presentation or both
    implementation(platform(libs.firebase))
    api(libs.firebase.auth)

}