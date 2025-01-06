package com.test.kmp.todo.app

import kotlinx.coroutines.flow.flow
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

object Test {
    val testFlow = flow<Int> {

    }
}