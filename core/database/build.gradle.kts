plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // Ksp
    alias(libs.plugins.devtools.ksp)

    // Dagger Hilt
    alias(libs.plugins.dagger.hilt.android)

    // Google Services
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.projectlab.core.database"
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

    // Firebase -> Firestore
    api(platform(libs.firebase))
    api(libs.firebase.firestore)

    // Dagger Hilt + Ksp
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Core System
    implementation(projects.core.domain)
    // TODO : review this module dependency:
    // implementation(projects.core.data)

    // modules
    // implementation(project(":core:domain"))
    // implementation(projects.core.domain)
}