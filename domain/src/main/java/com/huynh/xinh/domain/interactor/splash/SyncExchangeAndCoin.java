package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.repositories.CoinRepository;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public class SyncExchangeAndCoin extends UseCase<Boolean, Void> {
    private final ExchangeRepository exchangeRepository;
    private final CoinRepository coinRepository;

    @Inject
    protected SyncExchangeAndCoin(ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread,
                                  ExchangeRepository exchangeRepository,
                                  CoinRepository coinRepository) {
        super(threadExecutor, postExecutionThread);
        this.exchangeRepository = exchangeRepository;
        this.coinRepository = coinRepository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Void aVoid) {
        return Observable.zip(exchangeRepository.syncExchanges(), coinRepository.syncCoins(),
                (syncExchangeSuccess, syncCoinSuccess)-> syncExchangeSuccess && syncCoinSuccess);
    }
}
