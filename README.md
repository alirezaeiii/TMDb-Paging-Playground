# TMDb-Paging
A sample to showcase Kotlin, MVVM, Paging, Dagger, RxJava, Coroutines, Jetpack Compose, Retrofit, DataBinding, MotionLayout, Espresso and Unit test.

<a href="https://play.google.com/store/apps/details?id=com.sample.android.tmdb"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=70px /></a>

## Screenshots
<p float="left">
  <img src="https://github.com/alirezaeiii/TMDb-Paging/blob/master/screenshots/screenshot1.gif" width="249" />
  <img src="https://github.com/alirezaeiii/TMDb-Paging/blob/master/screenshots/screenshot2.gif" width="249" />
  <img src="https://github.com/alirezaeiii/TMDb-Paging/blob/master/screenshots/screenshot3.gif" width="249" />
  <img src="https://github.com/alirezaeiii/TMDb-Paging/blob/master/screenshots/screenshot4.gif" width="249" />
</p>

## Features
* MVVM Architecture.
* Jetpack Libraries and Architecture Components.

## Prerequisites

Add your [TMDB](https://www.themoviedb.org/) API key in the `local.properties` file:
```
tmdb_api_key=YOUR_API_KEY
```

## Testing
Local unit testing is done for Movie, TVShow and Search [PageKeyRepositories](https://github.com/Ali-Rezaei/TMDb-Paging/blob/master/app/src/main/java/com/sample/android/tmdb/paging/BasePageKeyRepository.kt), Movie and TVShow [DetailViewModels](https://github.com/Ali-Rezaei/TMDb-Paging/blob/master/app/src/main/java/com/sample/android/tmdb/ui/detail/DetailViewModel.kt) as well as [PersonViewModel](https://github.com/Ali-Rezaei/TMDb-Paging/blob/master/app/src/main/java/com/sample/android/tmdb/ui/person/PersonViewModel.kt).

## Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [Paging](https://developer.android.com/topic/libraries/architecture/paging) Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.
   * [Compose](https://developer.android.com/jetpack/compose) Android’s modern toolkit for building native UI.
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
   * [DataBinding](https://developer.android.com/topic/libraries/data-binding/) is a Library in the support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
   * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is lifecycle-aware, meaning it respects the lifecycle of other app components updating app component observers that are in an active lifecycle state.
* [Glide](https://github.com/bumptech/glide) is an image loading and caching library for Android.
* [Dagger](https://github.com/google/dagger) is a fully static, compile-time dependency injection framework for Java, Kotlin, and Android.
* [RxJava](https://github.com/ReactiveX/RxJava) is a library for composing asynchronous code using observable sequences.
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) is a module that adds the minimum classes to RxJava to make writing reactive components in Android.
* [Kotlin coroutines](https://developer.android.com/kotlin/coroutines) Executing code asynchronously.
* [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) is a state-holder observable flow that emits the current and new state updates to its collectors.
* [Coil-compose](https://coil-kt.github.io/coil/compose/) An image loading library for Android backed by Kotlin Coroutines.
* [Retrofit](https://square.github.io/retrofit/) is a Type-safe HTTP client for Android, Java and Kotlin by Square.
* [Gson](https://github.com/google/gson) is a serialization/deserialization library to convert objects into JSON and back.
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses.
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android.
* [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout) helps to manage motion and widget animation.
* [Mockito](https://github.com/mockito/mockito) which is the most popular Mocking framework for unit tests written in Java as well as Kotlin.
* [MockK](https://mockk.io/ANDROID.html) mocking library for Kotlin
* [Espresso](https://developer.android.com/training/testing/espresso) Automated testing UI test

## Licence
    MIT License

    Copyright (c) 2018 Mohammadali Rezaei

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

