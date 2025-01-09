package com.test.kmp.todo.app.games.domain

import com.test.kmp.todo.app.games.data.GamesRepository
import com.test.kmp.todo.app.data.models.GameDealLookupResponse

interface GetGameLookupUseCase {
    suspend operator fun invoke(dealId: String): Result<GameDealLookupResponse>
}

class GetGameLookupUseCaseImpl(
    private val gamesRepository: GamesRepository
): GetGameLookupUseCase {
    override suspend fun invoke(dealId: String): Result<GameDealLookupResponse> {
        return gamesRepository.getGameLookup(dealId)
    }
}