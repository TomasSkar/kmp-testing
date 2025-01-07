package com.test.kmp.todo.app.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.test.kmp.todo.app.data.models.Task

const val DATABASE_PATH = "todo_app_database_path.db"

@Database(entities = [Task::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun getTasksDao(): TasksDao
    override fun clearAllTables(): Unit {}
}

interface DB {
    fun clearAllTables(): Unit {}
}