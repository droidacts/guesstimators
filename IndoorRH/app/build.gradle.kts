plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "org.xluz.droidacts.guesstimators.IndoorRH"
    compileSdk = 37

    defaultConfig {
        applicationId = "org.xluz.droidacts.guesstimators.IndoorRH"
        minSdk = 25
        targetSdk = 36
        versionCode = 11
        versionName = "1.1.5"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
