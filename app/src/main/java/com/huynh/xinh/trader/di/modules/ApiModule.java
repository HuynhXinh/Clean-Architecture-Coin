package com.huynh.xinh.trader.di.modules;

import com.huynh.xinh.data.repositories.coin.cloud.GetCoinApi;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeApi;
import com.huynh.xinh.data.repositories.market.cloud.GetMarketApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides
    @Singleton
    GetCoinApi getCoinApi(Retrofit retrofit) {
        return retrofit.create(GetCoinApi.class);
    }

    @Provides
    @Singleton
    ExchangeApi exchangeApi(Retrofit retrofit) {
        return retrofit.create(ExchangeApi.class);
    }

    @Provides
    @Singleton
    GetMarketApi marketApi(Retrofit retrofit) {
        return retrofit.create(GetMarketApi.class);
    }
}
