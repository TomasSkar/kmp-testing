package com.test.kmp.todo.app.utils

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class JsonUtils() {
    val jsonResolver = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }

    inline fun <reified T> toJson(model: T): String {
        return try {
            val serializer = serializer<T>()
            jsonResolver.encodeToString(serializer, model)
        } catch (e: SerializationException) {
            // Handle serialization errors (e.g., log the error, return an empty string, etc.)
            println("Error serializing to JSON: ${e.message}")
            "" // Or handle the error as appropriate
        }
    }

    inline fun <reified T> fromJson(jsonString:String): T? {
        return try {
            jsonResolver.decodeFromString(jsonString)
        } catch (e: SerializationException) {
            // Handle deserialization errors (e.g., log the error, return null, etc.)
            println("Error deserializing from JSON: ${e.message}")
            null // Or handle the error as appropriate
        }
    }

}
