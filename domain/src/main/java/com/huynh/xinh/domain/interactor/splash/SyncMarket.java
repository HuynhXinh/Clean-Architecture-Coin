package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.repositories.ExchangeRepository;
import com.huynh.xinh.domain.repositories.MarketRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public class SyncMarket extends UseCase<Boolean, Void> {
    private final ExchangeRepository exchangeRepository;
    private final MarketRepository marketRepository;

    @Inject
    protected SyncMarket(ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread,
                         ExchangeRepository exchangeRepository,
                         MarketRepository marketRepository) {
        super(threadExecutor, postExecutionThread);
        this.exchangeRepository = exchangeRepository;
        this.marketRepository = marketRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Void aVoid) {
        return exchangeRepository.getExchanges()
                .flatMapIterable(exchanges -> exchanges)
                .flatMap(exchange -> marketRepository.syncMarkets(exchange.getRoute()))
                .map(syncMarketSuccess -> syncMarketSuccess);
    }
}
