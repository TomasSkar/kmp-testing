package com.test.kmp.todo.app.di

import com.test.kmp.todo.app.add.AddTaskViewModel
import com.test.kmp.todo.app.edit.EditTaskViewModel
import com.test.kmp.todo.app.details.TaskDetailsViewModel
import com.test.kmp.todo.app.home.TasksListViewModel
import com.test.kmp.todo.app.finished.FinishedTasksListViewModel
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.TasksRepositoryImpl
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    single<TasksRepository> { TasksRepositoryImpl() }
}

val vmModule = module {
    viewModelOf(::TasksListViewModel)
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::TaskDetailsViewModel)
    viewModelOf(::FinishedTasksListViewModel)
    viewModelOf(::EditTaskViewModel)
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule, vmModule)
    }
}