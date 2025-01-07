package com.test.kmp.todo.app.di

import com.test.kmp.todo.app.data.AppDatabase
import com.test.kmp.todo.app.db.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
    single<AppDatabase> { getDatabaseBuilder(get()) }
}