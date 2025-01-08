package com.test.kmp.todo.app.network.services

import com.test.kmp.todo.app.data.models.Emoji
import com.test.kmp.todo.app.data.models.EmojiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface EmojiService {
    suspend fun getAllEmojis(): Result<List<Emoji>>
}

class EmojiServiceImpl(private val client: HttpClient): EmojiService{
    override suspend fun getAllEmojis(): Result<List<Emoji>> {
        return try {
            val result = client.get("all").body<List<Emoji>>()
            Result.success(result)
        } catch (e: Exception) {
            if(e is CancellationException) throw e
            Result.failure(e)
        }
    }
}
