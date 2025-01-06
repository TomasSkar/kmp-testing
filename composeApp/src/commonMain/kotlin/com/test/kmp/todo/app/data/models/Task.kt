package com.test.kmp.todo.app.data.models

// Todo maybe @Serializable
data class Task(
    val id: String,
    val name: String,
    val description: String,
    val isFinished: Boolean = false
)
