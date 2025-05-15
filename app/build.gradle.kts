import java.io.File
import java.io.FileInputStream
import java.util.*
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}
val prop = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

val rapidApiKey = prop.getProperty("RAPID_API_KEY")
val rapidApiHostOne = prop.getProperty("RAPID_API_HOST_ONE")
val rapidApiHostTwo = prop.getProperty("RAPID_API_HOST_TWO")
val rapidApiHostThree = prop.getProperty("RAPID_API_HOST_THREE")
val rapidApiHostFour = prop.getProperty("RAPID_API_HOST_FOUR")


android {
    namespace = "com.example.filmsdataapp"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "RAPID_API_KEY", "\"$rapidApiKey\"")
        buildConfigField("String", "RAPID_API_HOST_ONE", "\"$rapidApiHostOne\"")
        buildConfigField("String", "RAPID_API_HOST_TWO", "\"$rapidApiHostTwo\"")
        buildConfigField("String", "RAPID_API_HOST_THREE", "\"$rapidApiHostThree\"")
        buildConfigField("String", "RAPID_API_HOST_FOUR", "\"$rapidApiHostFour\"")
        applicationId = "com.example.filmsdataapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "34.0.0"
}

dependencies {
    val nav_version = "2.8.9"
    val okhttp_version = "4.12.0"
    implementation("com.google.accompanist:accompanist-placeholder-material:0.34.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.0")
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.33.2-alpha")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}