package com.test.kmp.todo.app.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun convertTimestampToHumanReadable(
    timestamp: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): String {
    val instant = Instant.fromEpochSeconds(timestamp)

    val dateTime = instant.toLocalDateTime(timeZone)

    return dateTime.date.toString()
}