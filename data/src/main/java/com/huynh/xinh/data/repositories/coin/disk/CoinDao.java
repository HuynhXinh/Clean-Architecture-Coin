package com.huynh.xinh.data.repositories.coin.disk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<CoinEntity> coinEntities);

    @Query("SELECT * FROM COIN ")
    Maybe<List<CoinEntity>> getAllCoins();
}
