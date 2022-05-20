object ApplicationId {
    val sample = "com.d1.altusdk.sample"
}

object Modules {
    val app = ":app"
    val sdkCore = ":altu-sdk-core"
    val sdkComponents = ":altu-sdk-components"
}

object Releases {
    val versionCode = 1
    val versionName = "1"
}

object Versions {
    val kotlin = "1.4.31"
    val gradle = "4.0.1"
    val compileSdk = 31
    val minSdk = 21
    val targetSdk = 31
    val androidxCore = "1.7.0"
    val appcompat = "1.4.1"
    val coroutines = "1.3.5"
    val constraintLayout = "2.0.4"
    val junit = "4.12"
    val androidxJunit = "1.1.2"
    val espresso = "3.3.0"
    val lifecycle = "2.5.0-alpha04"
    val material = "1.5.0"
    val gson = "2.8.9"
    val roomVersion = "2.4.1"
    val okHttp = "4.9.3"
    val websocket = "2.14"
    val mockk = "1.12.3"
    val robolectric = "4.4"
    val mockwebserver = "4.9.0"
    val junitKtx = "1.1.3"
    val glide = "4.13.0"
    val d1PushSdk = "0.8.10"
    val firebaseBom = "29.0.3"
}

object Libraries {
    val xconstraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val xappcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val liveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val websocket = "com.neovisionaries:nv-websocket-client:${Versions.websocket}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    val firebaseMessagingKtx = "com.google.firebase:firebase-messaging-ktx"
}

object D1 {
    val pushSdk = "cx.d1:pushsdk:${Versions.d1PushSdk}"
}

object Android {
    val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}

object AndroidTest {
    val junit = "junit:junit:${Versions.junit}"
    val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"
    val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
}