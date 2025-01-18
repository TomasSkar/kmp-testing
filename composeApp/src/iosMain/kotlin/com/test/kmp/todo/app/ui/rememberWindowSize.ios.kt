package com.test.kmp.todo.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenWidth(): Dp {
    val localWindowInfo = LocalWindowInfo.current
    val localDensity = LocalDensity.current
    val configuration = localWindowInfo.containerSize

    return with(localDensity) { configuration.width.toDp() }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenHeight(): Dp {
    val localWindowInfo = LocalWindowInfo.current
    val localDensity = LocalDensity.current
    val configuration = localWindowInfo.containerSize

    return with(localDensity) { configuration.height.toDp() }
}