import java.util.Properties
val properties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    properties.load(localPropertiesFile.inputStream())
}

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.igaworks.adbrixjavasample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.igaworks.adbrixjavasample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "ADBRIX_APPLICATION_KEY", properties.getProperty("ADBRIX_APPLICATION_KEY", ""))
        buildConfigField("String", "ADBRIX_SECRET_KEY", properties.getProperty("ADBRIX_SECRET_KEY", ""))
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
    buildFeatures{
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.ads.identifier)
    implementation(libs.installreferrer)
    implementation (libs.android.sdk)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}