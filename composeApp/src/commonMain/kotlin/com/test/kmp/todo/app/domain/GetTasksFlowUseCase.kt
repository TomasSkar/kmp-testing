package com.test.kmp.todo.app.domain

import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.Flow

interface GetTasksFlowUseCase {
    suspend operator fun invoke(): Flow<List<Task>>
}

class GetTasksFlowUseCaseImpl(private val repository: TasksRepository): GetTasksFlowUseCase {
    override suspend fun invoke(): Flow<List<Task>> {
        return repository.getTasksFlow()
    }
}
