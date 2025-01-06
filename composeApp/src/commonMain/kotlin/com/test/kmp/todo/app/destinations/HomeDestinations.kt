package com.test.kmp.todo.app.destinations

import kotlinx.serialization.Serializable

object HomeDestinations {
    @Serializable
    object TasksRoute

    @Serializable
    object TaskList
}