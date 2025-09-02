package com.github.xserxses.orderbook

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform