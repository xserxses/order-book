package com.github.xserxses.orderbook.ui.model

sealed class ScreenState<T> {
    class Loading<T> : ScreenState<T>()

    data class Ui<T>(
        val data: T,
    ) : ScreenState<T>()

    class Error<T> : ScreenState<T>()
}
