package com.test.kmp.todo.app.network

import com.test.kmp.todo.app.utils.JsonUtils
import com.test.kmp.todo.app.network.services.EmojiService
import com.test.kmp.todo.app.network.services.EmojiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerializationException
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val TIME_OUT = 12000L

fun HttpClientConfig<*>.responseLogger(isDebugApp: Boolean){
    install(Logging) {
        if (isDebugApp) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("RefreshTokenHttpLogging: $message")
                }
            }
        }
    }
}

val networkModule = module {
    singleOf(::JsonUtils)
    single {
        HttpClient {
            val jsonUtils: JsonUtils = get()

            defaultRequest {
                url("https://emojihub.yurace.pro/api/")
            }

            install(ContentNegotiation) {
                json(jsonUtils.jsonResolver)
            }

            // Headers
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, Json)
            }

            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
            }

            responseLogger(isDebugApp = true)
        }
    }
    singleOf(::EmojiServiceImpl).bind(EmojiService::class)
}
