package com.test.kmp.todo.app

import androidx.compose.runtime.Composable
import com.test.kmp.todo.app.ui.regular.RegularScreenEntry

@Composable
fun MainScreen() {
    // Todo idea maybe latter have separate implementation for very big
    // or for very small screens
    RegularScreenEntry()
}
