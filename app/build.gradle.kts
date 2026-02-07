plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.rajan.CoffeeShop" // changed to match source package
    // set compileSdk to 36 to satisfy AAR metadata requirements from current androidx libraries
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rajan.CoffeeShop" // keep applicationId consistent with namespace
        minSdk = 24
        // align targetSdk with compileSdk for consistency with libraries
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        compose = true
    }
}

// Kotlin compiler configuration using the new compilerOptions DSL (Kotlin 2.0+)
kotlin {
    jvmToolchain(11)
    compilerOptions {
        // set the JVM target via the DSL (avoid the deprecated kotlinOptions.jvmTarget string usage)
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        // keep default free compiler args for Compose if needed; add extras here if you rely on them
        freeCompilerArgs.addAll(listOf("-opt-in=kotlin.RequiresOptIn"))
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Use explicit, versioned artifacts for Material icons to ensure availability
    implementation("androidx.compose.material:material-icons-core:1.5.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    // DataStore Preferences for onboarding flag storage
    implementation("androidx.datastore:datastore-preferences:1.1.0")
    // Coroutines Android for safe coroutine scope usage on Android
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}