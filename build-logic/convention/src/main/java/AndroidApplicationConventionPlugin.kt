import com.android.build.api.dsl.ApplicationExtension
import com.kotlinpl.convention.ExtensionType
import com.kotlinpl.convention.configureBuildTypes
import com.kotlinpl.convention.configureKotlinAndroid
import com.kotlinpl.convention.libs
import com.kotlinpl.convention.version
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure


class AndroidApplicationConventionPlugin : Plugin <Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "kotlin-android")

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.version("projectTargetSdk").toInt()

                configureBuildTypes(this, ExtensionType.APPLICATION)
            }
        }
    }
}