import com.android.build.api.dsl.ApplicationExtension
import com.kotlinpl.convention.configureAndroidCompose
import com.kotlinpl.convention.getLibrary
import com.kotlinpl.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin <Project> {
    override fun apply(target: Project) {
        with(target) {
            // ApplicationConventionModule applied on any compose module
            pluginManager.apply("travelinandroid.android.application")

            val extension = extensions.getByType<ApplicationExtension>()

            configureAndroidCompose(extension)

            dependencies {
                "implementation"(libs.getLibrary("androidx-activity-compose"))
            }
        }
    }
}