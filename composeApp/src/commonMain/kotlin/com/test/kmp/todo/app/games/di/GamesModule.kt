package com.test.kmp.todo.app.games.di

import com.test.kmp.todo.app.games.domain.GetAllDealsUseCase
import com.test.kmp.todo.app.games.domain.GetAllDealsUseCaseImpl
import com.test.kmp.todo.app.games.domain.GetGameLookupUseCase
import com.test.kmp.todo.app.games.domain.GetGameLookupUseCaseImpl
import com.test.kmp.todo.app.games.GamesViewModel
import com.test.kmp.todo.app.games.data.GamesRepository
import com.test.kmp.todo.app.games.data.GamesRepositoryImpl
import com.test.kmp.todo.app.games.lookup.GamesLookupViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val gamesModule = module {
    singleOf(::GamesRepositoryImpl).bind(GamesRepository::class)
    singleOf(::GetAllDealsUseCaseImpl).bind(GetAllDealsUseCase::class)
    singleOf(::GetGameLookupUseCaseImpl).bind(GetGameLookupUseCase::class)
    viewModelOf(::GamesViewModel)
    viewModelOf(::GamesLookupViewModel)
}