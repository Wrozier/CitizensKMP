import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.sqldelight)
}

kotlin {
    // Suppress expect/actual classes Beta warning
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.all {
            linkerOpts("-lsqlite3")
            linkerOpts("-Xlinker", "-ld_classic")
            linkerOpts("-Xlinker", "-no_warn_duplicate_libraries")
            linkerOpts("-Xlinker", "-no_warn_search_path")
            linkerOpts("-Xlinker", "-not_allowed_client_warning")
        }
        
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            export(libs.koin.core)
            export(libs.sqldelight.native.driver)
        }
    }
    
    android {
       namespace = "com.tc.citizenskmp.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
    
       compilerOptions {
           jvmTarget.set(JvmTarget.JVM_11)
           freeCompilerArgs.add("-Xexpect-actual-classes")
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.sqldelight.android.driver)
            implementation(libs.koin.android)
            implementation(libs.androidx.security.crypto)
            implementation(libs.compose.uiToolingPreview)
        }
        commonMain.dependencies {
            api(projects.domain)
            // api(projects.data) // Removed to break circular dependency: data -> shared -> data
            // implementation(projects.featureAuth) // Removed to break circular dependency: feature-auth -> shared -> feature-auth

             implementation(projects.featureAccounts)
             implementation(projects.featureTransactions)
             implementation(projects.featureCheckDeposit)
             implementation(projects.featureP2pTransfer)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            api(libs.koin.core)
        }
        iosMain.dependencies {
            api(libs.sqldelight.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("CitizensDatabase") {
            packageName.set("com.citizens.db")
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}
