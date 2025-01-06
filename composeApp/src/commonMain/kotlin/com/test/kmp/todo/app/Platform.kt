package com.test.kmp.todo.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform