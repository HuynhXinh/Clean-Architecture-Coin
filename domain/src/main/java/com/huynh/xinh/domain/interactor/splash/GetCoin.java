package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCoin extends UseCase<List<Coin>, Void> {
    private final CoinRepository coinRepository;

    @Inject
    public GetCoin(ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread,
                   CoinRepository coinRepository) {
        super(threadExecutor, postExecutionThread);
        this.coinRepository = coinRepository;
    }

    @Override
    public Observable<List<Coin>> buildUseCaseObservable(Void aVoid) {
        return coinRepository.getCoins();
    }
}
