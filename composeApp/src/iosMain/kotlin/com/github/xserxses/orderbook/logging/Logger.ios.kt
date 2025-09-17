package com.github.xserxses.orderbook.logging

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogWriter
import co.touchlab.kermit.Severity
import co.touchlab.kermit.StaticConfig

actual fun createLogger(): Logger =
    Logger(
        config =
            StaticConfig(
                minSeverity = Severity.Debug,
                logWriterList =
                    listOf(
                        NSLogWriter(),
                    ),
            ),
        tag = "DefaultAppTag",
    )
