plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.tc.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(projects.shared)
    implementation(projects.domain)
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.sqlDelight.runtime)
    implementation(libs.sqldelight.coroutines)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin.core)
    
    testImplementation(libs.junit)
}
