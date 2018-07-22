package com.huynh.xinh.data.database;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.huynh.xinh.data.repositories.coin.disk.CoinDao;
import com.huynh.xinh.data.repositories.coin.disk.CoinEntity;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeDao;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeEntity;
import com.huynh.xinh.data.repositories.market.disk.MarketDao;
import com.huynh.xinh.data.repositories.market.disk.MarketEntity;

@Database(
        version = DatabaseConfig.DB_VERSION,
        entities = {
                ExchangeEntity.class,
                CoinEntity.class,
                MarketEntity.class
        })
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase createAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, DatabaseConfig.DB_NAME)
                .build();
    }

    public abstract ExchangeDao getExchangeDao();

    public abstract CoinDao getCoinDao();

    public abstract MarketDao getMarketDao();
}
