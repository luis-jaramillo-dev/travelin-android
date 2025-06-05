import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.kotlinpl.convention.configureJacocoAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class JacocoAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "jacoco")

            val extension = extensions.getByType<LibraryExtension>()

            extension.buildTypes.configureEach {
                enableAndroidTestCoverage = true
                enableUnitTestCoverage = true
            }

            configureJacocoAndroid(extensions.getByType<LibraryAndroidComponentsExtension>())
        }
    }
}