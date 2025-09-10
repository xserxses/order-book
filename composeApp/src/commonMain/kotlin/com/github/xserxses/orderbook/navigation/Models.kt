package com.github.xserxses.orderbook.navigation

import kotlinx.serialization.Serializable
import orderbook.composeapp.generated.resources.Res
import orderbook.composeapp.generated.resources.book_label
import orderbook.composeapp.generated.resources.history_label
import orderbook.composeapp.generated.resources.ic_book
import orderbook.composeapp.generated.resources.ic_history
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed interface BottomNavItem {
    val icon: DrawableResource
    val label: StringResource
}

@Serializable
object OrderBook : BottomNavItem {
    override val icon = Res.drawable.ic_book
    override val label = Res.string.book_label
}

@Serializable
object NewOrder

@Serializable
object TradeRecords : BottomNavItem {
    override val icon = Res.drawable.ic_history
    override val label = Res.string.history_label
}
