plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // Ksp
    alias(libs.plugins.devtools.ksp)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    //google services
    //alias(libs.plugins.google.services)

}

android {
    namespace = "com.projectlab.auth.data"
    compileSdk = 35

    kotlinOptions {
        jvmTarget = "11"
    }

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

    // Core System
    implementation(projects.core.domain)
    implementation(projects.auth.domain)

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}