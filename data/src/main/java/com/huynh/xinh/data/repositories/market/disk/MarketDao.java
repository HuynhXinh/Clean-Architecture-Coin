package com.huynh.xinh.data.repositories.market.disk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.huynh.xinh.domain.models.Market;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface MarketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<MarketEntity> marketEntities);

    @Query("SELECT * FROM MARKET WHERE exchange = :exchangeName")
    Maybe<List<MarketEntity>> getAllMarketByExchangeName(String exchangeName);

    @Query("SELECT * FROM MARKET WHERE exchange = :exchangeName AND active = 1 LIMIT :offset, :maxSize")
    Maybe<List<MarketEntity>> getMarkets(String exchangeName, int offset, int maxSize);
}
