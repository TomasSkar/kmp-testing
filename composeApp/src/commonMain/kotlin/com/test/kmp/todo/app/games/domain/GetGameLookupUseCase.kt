package com.test.kmp.todo.app.games.domain

import com.test.kmp.todo.app.games.data.GamesRepository
import com.test.kmp.todo.app.data.models.GameDeal

interface GetAllDealsUseCase {
    suspend operator fun invoke(): Result<List<GameDeal>>
}

class GetAllDealsUseCaseImpl(
    private val gamesRepository: GamesRepository
): GetAllDealsUseCase {
    override suspend fun invoke(): Result<List<GameDeal>> {
        return gamesRepository.getAllDeals()
    }
}