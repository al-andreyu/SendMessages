import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.engine.parameters.VisibilityModifier

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.dokka)
}

android {
    namespace = "com.example.sendmessage"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.sendmessage"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    // Libreria de github que permite crear una actividad about us
    implementation(libs.material.aboutus) {
        exclude(group = "com.android.support")
    }
}

// Configuración de rutas personalizadas para Dokka
dokka {
    dokkaPublications.configureEach {
        outputDirectory.set(file("../documentation"))
        suppressInheritedMembers.set(true)
    }
    dokkaSourceSets.configureEach {
        documentedVisibilities.set(
            listOf(
                VisibilityModifier.Public,
                VisibilityModifier.Protected,
                VisibilityModifier.Private,
                VisibilityModifier.Internal
            )
        )
    }
}
