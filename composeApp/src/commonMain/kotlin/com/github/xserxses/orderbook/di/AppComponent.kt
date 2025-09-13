package com.github.xserxses.orderbook.di

import com.github.xserxses.orderbook.repository.record.TradeRecordRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppComponent {
    @Provides
    fun provideTradeRecordRepository() = TradeRecordRepository()
}

@KmpComponentCreate
expect fun createKmp(): AppComponent
