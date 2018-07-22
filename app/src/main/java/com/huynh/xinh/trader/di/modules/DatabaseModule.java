package com.huynh.xinh.trader.di.modules;

import android.app.Application;

import com.huynh.xinh.data.database.AppDatabase;
import com.huynh.xinh.data.repositories.coin.disk.CoinDao;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeDao;
import com.huynh.xinh.data.repositories.market.disk.MarketDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class DatabaseModule {
    private AppDatabase appDatabase;

    public DatabaseModule(Application application) {
        appDatabase = AppDatabase.createAppDatabase(application);
    }

    @Singleton
    @Provides
    public AppDatabase providesAppDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    ExchangeDao providesExchangeDao(AppDatabase appDatabase) {
        return appDatabase.getExchangeDao();
    }

    @Singleton
    @Provides
    CoinDao providesCoinDao(AppDatabase appDatabase) {
        return appDatabase.getCoinDao();
    }

    @Singleton
    @Provides
    MarketDao providesMarketDao(AppDatabase appDatabase) {
        return appDatabase.getMarketDao();
    }

}
