package com.test.kmp.todo.app.emojis

import com.test.kmp.todo.app.data.TasksRepository
import com.test.kmp.todo.app.data.models.Emoji
import com.test.kmp.todo.app.data.models.EmojiResponse

interface GetAllEmojisUseCase {
    suspend operator fun invoke(): Result<List<Emoji>>
}

class GetAllEmojisUseCaseImps(
    private val repository: TasksRepository
): GetAllEmojisUseCase{
    override suspend fun invoke(): Result<List<Emoji>> {
        return repository.getAllEmojis()
    }
}