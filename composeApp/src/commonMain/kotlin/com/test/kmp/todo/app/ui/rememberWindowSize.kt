package com.test.kmp.todo.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberWindowSize(): WindowSizeInfo {
    val localWindowInfo = LocalWindowInfo.current
    val localDensity = LocalDensity.current
    val configuration = localWindowInfo.containerSize

    val widthSizeDp = with(localDensity) { configuration.width.toDp() }
    val heightSizeDp = with(localDensity) { configuration.height.toDp() }

    return WindowSizeInfo(
        widthSize = widthSizeDp,
        heightSize = heightSizeDp,
        widthType = when {
            widthSizeDp.value < 600 -> WindowType.Compact
            widthSizeDp.value < 840 -> WindowType.Medium
            else -> WindowType.Expanded
        },
        heightType = when {
            heightSizeDp.value < 480 -> WindowType.Compact
            heightSizeDp.value < 900 -> WindowType.Medium
            else -> WindowType.Expanded
        }
    )
}
data class WindowSizeInfo(
    val widthSize: Dp,
    val heightSize: Dp,
    val widthType: WindowType,
    val heightType: WindowType
)
enum class WindowType { Compact, Medium, Expanded }