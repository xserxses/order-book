package com.github.xserxses.orderbook

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.github.xserxses.orderbook.navigation.NewOrder
import com.github.xserxses.orderbook.navigation.OrderBook
import com.github.xserxses.orderbook.navigation.TradeRecords
import com.github.xserxses.orderbook.screen.book.OrderBookScreen
import com.github.xserxses.orderbook.screen.neworder.NewOrderScreen
import com.github.xserxses.orderbook.screen.records.TradeHistoryScreen
import com.github.xserxses.orderbook.ui.OrderBookTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    OrderBookTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text("Order Book")
                    },
                )
            },
            bottomBar = { BottomNav(navController) },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = OrderBook,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable<OrderBook> {
                    OrderBookScreen(
                        onNewOrder = {
                            navController.navigate(NewOrder)
                        },
                    )
                }
                dialog<NewOrder> {
                    NewOrderScreen()
                }
                composable<TradeRecords> { backStackEntry ->
                    TradeHistoryScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNav(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val items =
            remember {
                listOf(
                    OrderBook,
                    TradeRecords,
                )
            }

        items.forEach { screen ->
            val isCurrentSelected = currentRoute == screen::class.qualifiedName
            NavigationBarItem(
                selected = isCurrentSelected,
                onClick = {
                    navController.navigate(screen) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = vectorResource(screen.icon),
                        contentDescription = null,
                    )
                },
                label = { Text(text = stringResource(screen.label)) },
            )
        }
    }
}
