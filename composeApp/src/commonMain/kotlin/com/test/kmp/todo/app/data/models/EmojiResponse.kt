package com.test.kmp.todo.app.data.models

import kotlinx.serialization.Serializable

@Serializable
data class EmojiResponse(
    val data: List<Emoji>
)