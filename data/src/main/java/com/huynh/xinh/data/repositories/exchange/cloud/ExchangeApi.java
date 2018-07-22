package com.huynh.xinh.data.repositories.exchange.cloud;

import com.huynh.xinh.data.repositories.exchange.ListExchangeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public interface ExchangeApi {
    @GET("exchanges")
    Observable<ListExchangeResponse> getListExchangeMarket();
}
