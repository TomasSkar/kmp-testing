package com.test.kmp.todo.app.games.data

import com.test.kmp.todo.app.data.models.GameDeal
import com.test.kmp.todo.app.data.models.GameDealLookupResponse
import com.test.kmp.todo.app.network.services.GamesService

interface GamesRepository {
    suspend fun getAllDeals(): Result<List<GameDeal>>
    suspend fun getGameLookup(dealId: String): Result<GameDealLookupResponse>
}

class GamesRepositoryImpl(private val gamesService: GamesService) : GamesRepository {
    override suspend fun getAllDeals(): Result<List<GameDeal>> {
        return gamesService.getAllDeals()
    }

    override suspend fun getGameLookup(dealId: String): Result<GameDealLookupResponse> {
        return gamesService.getGameLookup(dealId)
    }
}
