package com.huynh.xinh.data.repositories.market.cloud;

import com.huynh.xinh.data.repositories.market.responses.DetailExchangeResponse;
import com.huynh.xinh.data.repositories.market.responses.ListMarketResponse;
import com.huynh.xinh.data.repositories.market.responses.MarketDetailResponse;
import com.huynh.xinh.data.repositories.market.responses.OhlcResponse;
import com.huynh.xinh.data.repositories.market.responses.SummaryResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GetMarketApi {
    @GET
    Observable<DetailExchangeResponse> getDetailExchange(@Url String routeExchange);

    @GET
    Observable<ListMarketResponse> getListMarket(@Url String routeMarket);

    @GET
    Observable<MarketDetailResponse> getMarketDetail(@Url String marketRoute);

    @GET
    Observable<SummaryResponse> getSummary(@Url String summaryUrl);

    @GET
    Observable<OhlcResponse> getOhlc(@Url String ohlcUrl,
                                     @Query("after") long after,
                                     @Query("periods") String periods);
}
