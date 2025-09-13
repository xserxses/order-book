package com.github.xserxses.orderbook.utils

import kotlin.time.Clock
import kotlin.time.ExperimentalTime

interface DateTimeProvider {
    fun currentTimeMillis(): Long
}

class DefaultDateTimeProvider : DateTimeProvider {
    @OptIn(ExperimentalTime::class)
    override fun currentTimeMillis(): Long =
        Clock
            .System
            .now()
            .toEpochMilliseconds()
}
