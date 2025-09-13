package com.github.xserxses.orderbook.di

import com.github.xserxses.orderbook.repository.order.OrderRepository
import com.github.xserxses.orderbook.repository.record.TradeRecordRepository
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides
import me.tatarka.inject.annotations.Scope

@Scope
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER)
annotation class Singleton

@Component
@Singleton
abstract class AppComponent {
    @Provides
    @Singleton
    fun provideTradeRecordRepository() = TradeRecordRepository()

    @Provides
    @Singleton
    fun provideOrderRepository() = OrderRepository()
}

@KmpComponentCreate
expect fun createKmp(): AppComponent
