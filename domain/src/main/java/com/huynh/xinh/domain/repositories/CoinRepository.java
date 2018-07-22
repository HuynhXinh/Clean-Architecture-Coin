package com.huynh.xinh.domain.repositories;

import com.huynh.xinh.domain.models.Coin;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public interface CoinRepository {
    Observable<Boolean> syncCoins();

    Observable<List<Coin>> getCoins();
}
