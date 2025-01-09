package com.test.kmp.todo.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameInfo(
    @SerialName("storeID")
    val storeID: String,
    @SerialName("gameID")
    val gameID: String,
    @SerialName("name")
    val name: String,
    @SerialName("steamAppID")
    val steamAppID: String?,
    @SerialName("salePrice")
    val salePrice: String?,
    @SerialName("retailPrice")
    val retailPrice: String?,
    @SerialName("steamRatingText")
    val steamRatingText: String?,
    @SerialName("steamRatingPercent")
    val steamRatingPercent: String?,
    @SerialName("steamRatingCount")
    val steamRatingCount: String?,
    @SerialName("metacriticScore")
    val metacriticScore: String?,
    @SerialName("metacriticLink")
    val metacriticLink: String?,
    @SerialName("releaseDate")
    val releaseDate: Long,
    @SerialName("publisher")
    val publisher: String?,
    @SerialName("steamworks")
    val steamworks: String? = null,
    @SerialName("thumb")
    val thumb: String
)
