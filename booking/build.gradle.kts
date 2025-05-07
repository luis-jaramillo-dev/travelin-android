plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    // Ksp
    alias(libs.plugins.devtools.ksp)
    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)
    // Compose
    alias(libs.plugins.compose.compiler)
    //fire base
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.projectlab.booking"
    compileSdk = 35

    defaultConfig {
        minSdk = 25
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
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    //hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    implementation(project(":booking:domain"))
    ksp(libs.hilt.android.compiler)


    //Retrofit
    implementation(libs.retrofit2)
    implementation(libs.converter.gson)
    //Fire store
    implementation(platform(libs.firebase))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)
}
