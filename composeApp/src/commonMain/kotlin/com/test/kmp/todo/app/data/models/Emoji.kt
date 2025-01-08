package com.test.kmp.todo.app.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    @SerialName("name") val name: String,
    @SerialName("category") val category: String,
    @SerialName("group") val group: String,
    @SerialName("htmlCode") val htmlCode: List<String>,
    @SerialName("unicode") val unicode: List<String>
)
