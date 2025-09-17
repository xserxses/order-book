package com.github.xserxses.orderbook

import androidx.compose.ui.window.ComposeUIViewController
import com.github.xserxses.orderbook.persistance.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController { App(getDatabaseBuilder()) }
