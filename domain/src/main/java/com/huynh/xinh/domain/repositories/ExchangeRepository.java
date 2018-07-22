package com.huynh.xinh.domain.repositories;

import com.huynh.xinh.domain.models.Exchange;

import java.util.List;

import io.reactivex.Observable;

public interface ExchangeRepository {

    Observable<Boolean> syncExchanges();

    Observable<List<Exchange>> getExchanges();
}
