import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

private val javaVersion = JvmTarget.JVM_17

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(javaVersion)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.androidx.navigation.compose)

            implementation(libs.koin.core)

            implementation(libs.kotlinx.datetime)

            implementation(libs.room.runtime)
            implementation(libs.sqlite.driver)
        }
    }

    dependencies {
        detektPlugins(libs.detekt.formatting)
        kspAndroid(libs.room.compiler)
        kspIosX64(libs.room.compiler)
        kspIosArm64(libs.room.compiler)
    }
}

android {
    namespace = "cz.johnyapps.eddiehostopky"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "cz.johnyapps.eddiehostopky"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    config.setFrom("$rootDir/detekt-config.yml")
    source.setFrom("$projectDir/src")
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = javaVersion.target
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = javaVersion.target
}

internal fun DependencyHandler.detektPlugins(dependencyNotation: Any): Dependency? =
    add("detektPlugins", dependencyNotation)

internal fun DependencyHandler.kspAndroid(dependencyNotation: Any): Dependency? =
    add("kspAndroid", dependencyNotation)

internal fun DependencyHandler.kspIosX64(dependencyNotation: Any): Dependency? =
    add("kspIosX64", dependencyNotation)

internal fun DependencyHandler.kspIosArm64(dependencyNotation: Any): Dependency? =
    add("kspIosArm64", dependencyNotation)
