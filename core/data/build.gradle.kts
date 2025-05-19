import java.util.Properties

plugins {
    alias(libs.plugins.travelinandroid.android.library)
    alias(libs.plugins.travelinandroid.android.hilt)

    // Proto DataStore
    alias(libs.plugins.protobuf)
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

//configurations.all {
//    resolutionStrategy.dependencySubstitution {
//        substitute(
//            module("com.google.protobuf:protobuf-javalite")
//        ).using(
//            module(libs.protobuf.javalite.get().toString())
//        )
//    }
//}

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

    // Proto DataStore
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }

    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        all().configureEach {
            builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}