import com.android.build.api.dsl.LibraryExtension
import com.kotlinpl.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "travelin.android.library")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            val extensions = extensions.getByType<LibraryExtension>()

            configureAndroidCompose(extensions)

        }
    }
}