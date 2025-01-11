package com.test.kmp.todo.app.db

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.test.kmp.todo.app.data.AppDatabase
import com.test.kmp.todo.app.data.DATABASE_PATH
import kotlinx.coroutines.Dispatchers
import java.io.File

fun getDatabaseBuilder(): AppDatabase {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DATABASE_PATH)
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    ).fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}