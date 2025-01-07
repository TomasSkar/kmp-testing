package com.test.kmp.todo.app.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.test.kmp.todo.app.data.AppDatabase
import com.test.kmp.todo.app.data.DATABASE_PATH
import kotlinx.coroutines.Dispatchers

fun getDatabaseBuilder(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath(DATABASE_PATH)
    return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
        .fallbackToDestructiveMigrationOnDowngrade(false)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}