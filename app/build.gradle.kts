import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

val apiKeyPropertiesFile = rootProject.file("key.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(apiKeyPropertiesFile.inputStream())

android {
    namespace = "com.farzin.imdb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.farzin.imdb"
        minSdk = 25
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", apiKeyProperties.getProperty("API_KEY"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")


    //hilt di
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")


    //compose navigation
    implementation ("androidx.navigation:navigation-compose:2.7.7")

    //animation
    implementation ("com.airbnb.android:lottie-compose:5.2.0")

    //coil - load image from url
    implementation ("io.coil-kt:coil-compose:2.2.2")

    //swipe refresh
    implementation( "com.google.accompanist:accompanist-swiperefresh:0.30.0")

    //system ui controller
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    //datastore
    implementation ("androidx.datastore:datastore-preferences:1.1.0")

    //pager
    implementation ("com.google.accompanist:accompanist-pager:0.29.0-alpha")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.29.0-alpha")

    //room
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")

    // paging
    implementation ("androidx.paging:paging-runtime-ktx:3.3.0-beta01")
    implementation ("androidx.paging:paging-compose:3.3.0-beta01")

    // youtube player
    implementation("io.github.ilyapavlovskii:youtubeplayer-compose:2023.11.16")
//    implementation("com.chrynan.uri:uri-core:0.3.3")
//    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.0")

    // exo player
//    implementation ("androidx.media3:media3-exoplayer:1.2.0")
//    implementation ("androidx.media3:media3-exoplayer-dash:1.2.0")
//    implementation ("androidx.media3:media3-ui:1.2.0")

    // zoomable image
//    implementation("me.saket.telephoto:zoomable:0.7.1")
    implementation("me.saket.telephoto:zoomable-image-coil:0.7.1")


    //paging3
    implementation ("androidx.paging:paging-compose:3.3.0-beta01")



}