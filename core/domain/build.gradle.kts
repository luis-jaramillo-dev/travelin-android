plugins {
    alias(libs.plugins.travelinandroid.jvm.library)

    // Ksp
    alias(libs.plugins.devtools.ksp)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)
}
