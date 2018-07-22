package com.huynh.xinh.data.repositories.coin.cloud;

import com.huynh.xinh.data.repositories.coin.ListCoinResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public interface GetCoinApi {
    @GET("assets")
    Observable<ListCoinResponse> getListCoin();
}
