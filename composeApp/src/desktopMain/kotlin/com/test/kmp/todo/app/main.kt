package com.test.kmp.todo.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.test.kmp.todo.app.di.initKoin
import org.koin.core.context.GlobalContext.startKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject",
        ) {
            App()
        }
    }
}