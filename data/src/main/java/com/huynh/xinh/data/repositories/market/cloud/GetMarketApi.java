package com.huynh.xinh.data.repositories.market.cloud;

import com.huynh.xinh.data.repositories.market.responses.ListMarketResponse;
import com.huynh.xinh.data.repositories.market.responses.OhlcResponse;
import com.huynh.xinh.data.repositories.market.responses.PairResponse;
import com.huynh.xinh.data.repositories.market.responses.SummaryResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetMarketApi {

    @GET("markets/{market-name}")
    Observable<ListMarketResponse> getListMarket(@Path("market-name") String marketName);

    @GET("pairs/{pair}")
    Observable<PairResponse> getPairs(@Path("pair") String pair);

    @GET("markets/{market-name}/{pair}/summary")
    Observable<SummaryResponse> getSummary(@Path("market-name") String marketName, @Path("pair") String pair);

    @GET("markets/{market-name}/{pair}/ohlc")
    Observable<OhlcResponse> getOhlc(@Path("market-name") String marketName,
                                     @Path("pair") String pair,
                                     @Query("after") long after,
                                     @Query("periods") String periods);
}
