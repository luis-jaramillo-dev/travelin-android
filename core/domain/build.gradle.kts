plugins {
    alias(libs.plugins.travelinandroid.jvm.library)

    // Ksp
    alias(libs.plugins.travelinandroid.android.hilt)

    // Proto DataStore
    alias(libs.plugins.protobuf)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
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
                try {
                    create("java") {
                        option("lite")
                    }
                } catch (_: Exception) {
                    getByName("java") {
                        option("lite")
                    }
                }
            }
        }
    }
}
