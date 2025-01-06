package com.test.kmp.todo.app

import androidx.compose.ui.window.ComposeUIViewController
import com.test.kmp.todo.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}