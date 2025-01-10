package com.test.kmp.todo.app.network.services

import com.test.kmp.todo.app.data.models.GameDeal
import com.test.kmp.todo.app.data.models.GameDealLookupResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface GamesService {
    suspend fun getAllDeals(): Result<List<GameDeal>>
    suspend fun getGameLookup(dealId: String): Result<GameDealLookupResponse>
}

class GamesServiceImpl(private val client: HttpClient): GamesService {
    //https://www.cheapshark.com/api/1.0/deals?storeID=1&upperPrice=15
    override suspend fun getAllDeals(): Result<List<GameDeal>> {
        return try {
            val result = client.get("deals").body<List<GameDeal>>()
            Result.success(result)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }

    //https://www.cheapshark.com/api/1.0/deals?id=X8sebHhbc1Ga0dTkgg59WgyM506af9oNZZJLU9uSrX8%3D
    override suspend fun getGameLookup(dealId: String): Result<GameDealLookupResponse> {
        return try {
            val result = client.get("deals?id=$dealId").body<GameDealLookupResponse>()
            Result.success(result)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }
}