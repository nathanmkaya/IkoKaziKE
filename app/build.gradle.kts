import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs")
}

val ALGOLIA_APP_ID: String by project
val ALGOLIA_API_KEY: String by project

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "dev.dita.ikokazike"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "ALGOLIA_APP_ID", ALGOLIA_APP_ID)
        buildConfigField("String", "ALGOLIA_API_KEY", ALGOLIA_API_KEY)
    }

    buildTypes {
        getByName("release") {
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
    (kotlinOptions as KotlinJvmOptions).apply {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildToolsVersion("29.0.2")

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.firebase:firebase-core:17.2.1")
    implementation("com.google.firebase:firebase-analytics:17.2.1")
    implementation("com.google.firebase:firebase-firestore:$firestoreVersion")
    implementation("com.google.firebase:firebase-firestore-ktx:$firestoreVersion")
    implementation("com.firebaseui:firebase-ui-firestore:6.0.2")
    implementation("androidx.paging:paging-runtime-ktx:$paging_version")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("androidx.navigation:navigation-fragment:2.1.0")
    implementation("androidx.navigation:navigation-ui:2.1.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.1.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.1.0")
    implementation("com.algolia:instantsearch-android:$instantsearch")
    // Koin for Android
    implementation("org.koin:koin-android:2.0.1")
    // or Koin for Lifecycle scoping
    implementation("org.koin:koin-androidx-scope:2.0.1")
    // or Koin for Android Architecture ViewModel
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
