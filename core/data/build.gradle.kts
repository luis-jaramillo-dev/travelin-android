import java.util.Properties

plugins {
    alias(libs.plugins.travelinandroid.android.library)
    alias(libs.plugins.travelinandroid.android.hilt)
}
val localProperties = Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
        load(localFile.inputStream())
    }
}

val apiKey = localProperties.getProperty("API_KEY") ?: ""
val apiSecret = localProperties.getProperty("API_SECRET") ?: ""
val baseUrl = localProperties.getProperty("BASE_URL") ?: ""

android {
    namespace = "com.projectlab.core.data"
    defaultConfig {
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "API_SECRET", "\"$apiSecret\"")
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
    }

    buildFeatures {
        buildConfig = true
    }

}

dependencies {

    // Core System
    implementation(projects.core.domain)
    implementation(projects.core.database)

    // Core
    implementation(libs.androidx.room.runtime)

    // Firebase -> Firestore
    implementation(platform(libs.firebase))
    implementation(libs.firebase.firestore)

    // Network
    implementation(libs.bundles.retrofit)
}