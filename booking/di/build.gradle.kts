

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
    namespace = "com.projectlab.booking.di"
    compileSdk = 35

    defaultConfig {
        minSdk = 25

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
    //hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(project(":booking:domain"))
    implementation(project(":booking:data"))
    implementation(project(":booking:presentation"))
    implementation(project(":core:data"))
    //Retrofit
    implementation(libs.retrofit2)
    implementation(libs.converter.gson)
    //Fire store
    implementation(platform(libs.firebase))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.viewmodel.compose)
}

