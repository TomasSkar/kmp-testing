package com.test.kmp.todo.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

// Currently can't be in common because: https://youtrack.jetbrains.com/issue/CMP-3963
@Composable
expect fun getScreenWidth(): Dp

@Composable
expect fun getScreenHeight(): Dp

@Composable
fun rememberWindowSize(): WindowSizeInfo {
    val widthSizeDp = getScreenWidth()
    val heightSizeDp = getScreenHeight()

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