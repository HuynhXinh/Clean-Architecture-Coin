package com.huynh.xinh.trader.di.modules;

import com.huynh.xinh.data.repositories.coin.CoinRepositoryImpl;
import com.huynh.xinh.data.repositories.exchange.ExchangeRepositoryImpl;
import com.huynh.xinh.data.repositories.market.MarketRepositoryImpl;
import com.huynh.xinh.domain.repositories.CoinRepository;
import com.huynh.xinh.domain.repositories.ExchangeRepository;
import com.huynh.xinh.domain.repositories.MarketRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract ExchangeRepository provideExchangeRepository(ExchangeRepositoryImpl impl);

    @Binds
    abstract CoinRepository provideCoinRepository(CoinRepositoryImpl impl);

    @Binds
    abstract MarketRepository provideMarketRepository(MarketRepositoryImpl impl);
}
