package com.test.kmp.todo.app.di

import com.test.kmp.todo.app.add.AddTaskViewModel
import com.test.kmp.todo.app.add.AddTaskUseCase
import com.test.kmp.todo.app.add.AddTaskUseCaseImpl
import com.test.kmp.todo.app.data.TaskLocalStore
import com.test.kmp.todo.app.data.TaskLocalStoreImpl
import com.test.kmp.todo.app.domain.UpdateTaskUseCase
import com.test.kmp.todo.app.domain.UpdateTaskUseCaseImpl
import com.test.kmp.todo.app.domain.GetTasksFlowUseCase
import com.test.kmp.todo.app.domain.GetTasksFlowUseCaseImpl
import com.test.kmp.todo.app.domain.GetTaskFlowUseCase
import com.test.kmp.todo.app.domain.GetTaskFlowUseCaseImpl
import com.test.kmp.todo.app.edit.EditTaskViewModel
import com.test.kmp.todo.app.details.TaskDetailsViewModel
import com.test.kmp.todo.app.home.TasksListViewModel
import com.test.kmp.todo.app.finished.FinishedTasksListViewModel
import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.TasksRepositoryImpl
import com.test.kmp.todo.app.network.networkModule
import com.test.kmp.todo.app.emojis.EmojisViewModel
import com.test.kmp.todo.app.emojis.GetAllEmojisUseCase
import com.test.kmp.todo.app.emojis.GetAllEmojisUseCaseImps
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

expect fun platformModule(): Module

val appModule = module {
    singleOf(::TasksRepositoryImpl).bind(TasksRepository::class)
    singleOf(::TaskLocalStoreImpl).bind(TaskLocalStore::class)
}

val vmModule = module {
    viewModelOf(::TasksListViewModel)
    viewModelOf(::AddTaskViewModel)
    viewModelOf(::TaskDetailsViewModel)
    viewModelOf(::FinishedTasksListViewModel)
    viewModelOf(::EditTaskViewModel)
    viewModelOf(::EmojisViewModel)
}

val useCasesModule = module {
    singleOf(::AddTaskUseCaseImpl).bind(AddTaskUseCase::class)
    singleOf(::UpdateTaskUseCaseImpl).bind(UpdateTaskUseCase::class)
    singleOf(::GetTasksFlowUseCaseImpl).bind(GetTasksFlowUseCase::class)
    singleOf(::GetTaskFlowUseCaseImpl).bind(GetTaskFlowUseCase::class)
    singleOf(::GetAllEmojisUseCaseImps).bind(GetAllEmojisUseCase::class)
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(appModule, useCasesModule, vmModule, networkModule, platformModule())
    }
}