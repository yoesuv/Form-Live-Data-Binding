plugins {
    alias(libs.plugins.android.application)
}

base {
    archivesName.set(
        providers.provider {
            "${android.defaultConfig.applicationId}-v${android.defaultConfig.versionName}"
        },
    )
}

android {
    val alias = providers.gradleProperty("KEY_ALIAS").get()
    val key = providers.gradleProperty("KEY_PASSWORD").get()
    val store = providers.gradleProperty("STORE_PASSWORD").get()

    signingConfigs {
        create("release") {
            keyAlias = alias
            keyPassword = key
            storeFile = file("../my-app-key.keystore")
            storePassword = store
        }
    }

    namespace = "com.yoesuv.formlivebinding"
    compileSdk {
        version =
            release(36) {
                minorApiLevel = 1
            }
    }

    defaultConfig {
        applicationId = "com.yoesuv.formlivebinding"
        minSdk = 24
        targetSdk = 36
        versionCode = 8
        versionName = "1.1.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
        resValues = true
    }
    flavorDimensions.add("default")
    productFlavors {
        create("forTest") {
            resValue("string", "app_name", "Form Live Binding TEST")
            applicationIdSuffix = ".test"
            dimension = "default"
        }
        create("production") {
            resValue("string", "app_name", "Form Live Binding")
            dimension = "default"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.livedata)
}
