This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Projects and articles used as example: 
1. Room: 
  - a) https://developer.android.com/kotlin/multiplatform/room
  - b) https://github.com/BillyMRX1/The-Notes-KMP/blob/production/composeApp/src/commonMain/kotlin/ui/components/EmptyState.kt
  - c) https://medium.com/@brilianadeputra/how-to-use-koin-and-room-database-in-kotlin-multiplatform-ce73577e4cc9
2. Kermit logging: 
  - a) https://kermit.touchlab.co/docs/
3. ViewModel: 
  - a) https://proandroiddev.com/how-to-integrate-viewmodel-in-kotlin-multiplatform-with-koin-1a1134530215
  - b) VM params: https://stackoverflow.com/questions/69456746/how-to-initialize-koin-viewmodel-with-parameters-inside-a-composable
4. Ktor: 
  - a) https://ktor.io/docs/client-engines.html#dependencies
  - b) https://proandroiddev.com/compose-multiplatform-networking-with-ktor-koin-part-2-ea394158feb9
5. Desktop support:
  - `add("kspDesktop", libs.room.compiler)` from [Stackoverflow](https://stackoverflow.com/questions/78858784/roomdatabaseconstructor-on-kotlinmultiplatform-has-no-corresponding-expected-dec)
  - Room setup from [Medium post](https://proandroiddev.com/storing-data-in-local-database-like-a-boss-introducing-room-in-compose-multiplatform-2e39781c7b6a)
  - overall setup just copied from new [wizard project](https://kmp.jetbrains.com/)
