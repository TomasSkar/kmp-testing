package com.test.kmp.todo.app.destinations

import kotlinx.serialization.Serializable

object DirectDestinations {
    @Serializable
    object AddTask

    @Serializable
    data class EditTask(val taskId: String)

    @Serializable
    data class TaskDetails(
        val taskId: String
    )
}