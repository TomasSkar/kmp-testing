package com.test.kmp.todo.app.destinations

import kotlinx.serialization.Serializable

object GamesDestinations {
    @Serializable
    object GamesRoute

    @Serializable
    object GamesDeals

    @Serializable
    data class GamesDealLookup(val dealId: String)
}