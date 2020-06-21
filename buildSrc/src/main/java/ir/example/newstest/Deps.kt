package ir.example.newstest

object Deps {

    object Modules {
        const val domain = ":domain"
        const val data = ":data"
    }

    object Core {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
        const val core_ktx = "androidx.core:core-ktx:${Versions.ktxVersion}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val googleMaterial =
            "com.google.android.material:material:${Versions.googleMaterialVersion}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
        const val supportFragment =
            "androidx.legacy:legacy-support-v4:${Versions.supportFragmentVersion}"


        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val buildGradle =
            "com.android.tools.build:gradle:${Versions.kotlinGradlePluginVersion}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val testRunner = "androidx.test.ext:junit:${Versions.testRunnerVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    }

    object Dagger {
        const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
        const val daggerAndroid =
            "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
        const val daggerAndroidCompiler =
            "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    }

    object Coroutine {
        const val coroutines_core =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineVersion}"
        const val coroutines_android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"
    }

    object LifeCycles {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val runtimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"
        const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val lifecycleCommon =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    }

    object Navigation {
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
        const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

        //for classpath
        const val navigationSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    }

    object ImageProcessing {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val converterGson =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val converterXml =
            "com.squareup.retrofit2:converter-simplexml:${Versions.retrofitVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
    }
}