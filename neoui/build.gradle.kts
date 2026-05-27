import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
}

kotlin {
    jvm()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "NeoUI"
            isStatic = true
        }
    }

    androidLibrary {
        namespace = "com.pushforcestudio.neoui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.kotlinx.coroutines.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

group = "com.pushforcestudio.neoui"
version = "0.4.0"

publishing {
    publications {
        named<MavenPublication>("kotlinMultiplatform") {
            artifactId = "core"
        }
        withType<MavenPublication> {
            pom {
                name.set("NeoUI")
                description.set("A Neo-Brutalist UI component library for Compose Multiplatform")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("pushforcestudio")
                        name.set("PushForce Studio")
                        email.set("pushforcestudio@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/emrepbu/NeoUI.git")
                    developerConnection.set("scm:git:ssh://github.com/emrepbu/NeoUI.git")
                    url.set("https://github.com/emrepbu/NeoUI")
                }
            }
        }
    }
}
