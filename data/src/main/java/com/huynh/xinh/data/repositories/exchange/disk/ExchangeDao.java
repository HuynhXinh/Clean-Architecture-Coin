package com.huynh.xinh.data.repositories.exchange.disk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface ExchangeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<ExchangeEntity> exchangeEntities);

    @Query("SELECT * FROM EXCHANGE WHERE active = 1 ORDER BY name ASC")
    Maybe<List<ExchangeEntity>> getAllExchanges();

    @Query("DELETE FROM EXCHANGE WHERE id NOT IN (:exchangeSupportIds) ")
    void deleteAllExchangeNotSupport(List<Long> exchangeSupportIds);
}
