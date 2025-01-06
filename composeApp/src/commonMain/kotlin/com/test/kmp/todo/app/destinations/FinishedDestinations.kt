package com.test.kmp.todo.app.destinations

import kotlinx.serialization.Serializable

object FinishedDestinations {
    @Serializable
    object FinishedTasksRoute

    @Serializable
    object FinishedTasks
}