package com.test.kmp.todo.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
actual fun getScreenWidth() = LocalConfiguration
    .current
    .screenWidthDp
    .dp

@Composable
actual fun getScreenHeight() = LocalConfiguration
    .current
    .screenHeightDp
    .dp