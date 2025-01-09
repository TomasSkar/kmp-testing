package com.test.kmp.todo.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheaperStore(
    @SerialName("dealID")
    val dealID: String,
    @SerialName("storeID")
    val storeID: String,
    @SerialName("salePrice")
    val salePrice: String,
    @SerialName("retailPrice")
    val retailPrice: String
)
