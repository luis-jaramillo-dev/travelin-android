plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    // Ksp
    alias(libs.plugins.devtools.ksp)

    // Google Services
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.projectlab.core.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Core
    implementation(libs.androidx.core.ktx)

    // Google services: Firebase products
    api(platform(libs.firebase))
    api(libs.firebase.firestore)

    // modules
    //implementation(project(":core:database"))

}
