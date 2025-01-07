package com.test.kmp.todo.app.data

import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskLocalStore {
    suspend fun getTasksFlow(): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun removeTask(taskId: Long)
    suspend fun getTask(taskId: Long): Flow<Task?>
}

class TaskLocalStoreImpl(
    private val appDatabase: AppDatabase
): TaskLocalStore {
    override suspend fun getTasksFlow(): Flow<List<Task>> {
        return appDatabase.getTasksDao().getAllTasksAsFlow()
    }

    override suspend fun addTask(task: Task) {
        appDatabase.getTasksDao().createTask(task)
    }

    override suspend fun updateTask(task: Task) {
        appDatabase.getTasksDao().updateTask(task)
    }

    override suspend fun removeTask(taskId: Long) {
        appDatabase.getTasksDao().deleteTaskById(taskId)
    }

    override suspend fun getTask(taskId: Long): Flow<Task?> {
        return appDatabase.getTasksDao().getTaskById(taskId)
    }

}