package com.test.kmp.todo.app.domain

import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.Flow

interface GetTaskFlowUseCase {
    suspend operator fun invoke(taskId: Long): Flow<Task?>
}

class GetTaskFlowUseCaseImpl(private val repository: TasksRepository): GetTaskFlowUseCase {
    override suspend fun invoke(taskId: Long): Flow<Task?> {
        return repository.getTask(taskId)
    }
}
