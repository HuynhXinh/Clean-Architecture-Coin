package com.huynh.xinh.domain.repositories;

import com.huynh.xinh.domain.models.Market;
import com.huynh.xinh.domain.models.MarketDetail;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Summary;

import java.util.List;

import io.reactivex.Observable;

public interface MarketRepository {
    Observable<Boolean> syncMarkets(String routeExchange);

    Observable<List<Market>> getMarkets(String exchangeName, int page, int maxResult);

    Observable<Summary> getSummary(String marketName, String pair);

    Observable<Ohlc> getOhlc(String marketName, String pair, long after, String periods);
}
