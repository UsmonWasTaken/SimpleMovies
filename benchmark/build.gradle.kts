plugins {
    id("com.android.test")
    id("kotlin-android")
}

android {
    compileSdk = 33
    targetProjectPath = ":app"
    namespace = "app.simple.movies.benchmark"
    val packageName = "app.simple.movies"

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "PACKAGE_NAME", "\"$packageName\"")
    }

    experimentalProperties["android.experimental.self-instrumenting"] = true

    buildTypes {
        val benchmark by registering {
            isDebuggable = true
            matchingFallbacks += "release"
            signingConfig = signingConfigs["debug"]
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

androidComponents {
    beforeVariants(selector().all()) {
        it.enable = it.buildType == "benchmark"
    }
}

dependencies {
    implementation("androidx.test.ext:junit:1.1.3")
    implementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("androidx.compose.ui:ui-test-junit4:1.2.1")
    implementation("androidx.test.uiautomator:uiautomator:2.2.0")
    implementation("androidx.benchmark:benchmark-macro-junit4:1.1.0-beta04")
}
