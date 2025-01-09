package com.test.kmp.todo.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDealLookupResponse(
    @SerialName("gameInfo")
    val gameInfo: GameInfo,
    @SerialName("cheaperStores")
    val cheaperStores: List<CheaperStore>,
    @SerialName("cheapestPrice")
    val cheapestPrice: CheapestPrice?
)
