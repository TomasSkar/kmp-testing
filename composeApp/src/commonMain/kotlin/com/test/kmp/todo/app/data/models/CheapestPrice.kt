package com.test.kmp.todo.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheapestPrice(
    @SerialName("price")
    val price: String? = null,
    @SerialName("date")
    val date: Long? = null
)
