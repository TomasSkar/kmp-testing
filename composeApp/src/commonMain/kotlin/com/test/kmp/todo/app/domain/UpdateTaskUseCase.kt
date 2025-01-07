package com.test.kmp.todo.app.domain

import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task

interface UpdateTaskUseCase {
    suspend operator fun invoke(task: Task)
}

class UpdateTaskUseCaseImpl(private val repository: TasksRepository): UpdateTaskUseCase {
    override suspend fun invoke(task: Task) {
        repository.updateTask(task)
    }
}
