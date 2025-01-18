package com.test.kmp.todo.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidth() = LocalWindowInfo
    .current
    .containerSize
    .width
    .dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight() = LocalWindowInfo
    .current
    .containerSize
    .height
    .dp