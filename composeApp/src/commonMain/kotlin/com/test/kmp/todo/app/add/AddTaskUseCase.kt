package com.test.kmp.todo.app.add

import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task

interface AddTaskUseCase {
    suspend operator fun invoke(task: Task)
}

class AddTaskUseCaseImpl(private val repository: TasksRepository): AddTaskUseCase{
    override suspend fun invoke(task: Task) = repository.addTask(task)
}