import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
}

android {
    compileSdk = 33

    namespace = "app.simple.movies"
    defaultConfig {
        applicationId = "app.simple.movies"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"

        setProperty("archivesBaseName", "Movies-v$versionName")
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties = gradleLocalProperties(rootDir)
        val tmdbApiKey = localProperties.getProperty("tmdb_api_key", "")
        buildConfigField("String", "TMDB_API_KEY", "\"$tmdbApiKey\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["debug"]
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += setOf("-opt-in=kotlin.RequiresOptIn", "-Xcontext-receivers")
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.navigation:navigation-compose:2.5.1")
    implementation("io.coil-kt:coil-compose:2.2.0")
    implementation("com.google.dagger:dagger:2.43.2")
    kapt("com.google.dagger:dagger-compiler:2.43.2")
    implementation("androidx.profileinstaller:profileinstaller:1.2.0")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha16")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.25.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-paging:2.5.0-alpha01")
    ksp("androidx.room:room-compiler:2.4.3")
    implementation("androidx.palette:palette-ktx:1.0.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.12.5")
    testImplementation("io.mockk:mockk-agent-jvm:1.12.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
}