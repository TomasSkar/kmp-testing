package com.test.kmp.todo.app.data

import com.test.kmp.todo.app.data.models.Emoji
import com.test.kmp.todo.app.data.models.EmojiResponse
import com.test.kmp.todo.app.data.models.Task
import com.test.kmp.todo.app.network.services.EmojiService
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    suspend fun getTasksFlow(): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun getTask(taskId: Long): Flow<Task?>
    suspend fun getAllEmojis(): Result<List<Emoji>>
}

class TasksRepositoryImpl(
    private val localStore: TaskLocalStore,
    private val emojiService: EmojiService,
) : TasksRepository {
    override suspend fun getTasksFlow() = localStore.getTasksFlow()

    override suspend fun addTask(task: Task) {
        localStore.addTask(task)
    }

    override suspend fun updateTask(task: Task) {
        localStore.updateTask(task)
    }

    override suspend fun getTask(taskId: Long): Flow<Task?> {
        return localStore.getTask(taskId)
    }

    override suspend fun getAllEmojis(): Result<List<Emoji>> {
        return emojiService.getAllEmojis()
    }
}
