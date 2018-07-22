package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetExchange extends UseCase<List<Exchange>, Void> {
    private final ExchangeRepository exchangeRepository;

    @Inject
    public GetExchange(ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       ExchangeRepository exchangeRepository) {
        super(threadExecutor, postExecutionThread);
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Observable<List<Exchange>> buildUseCaseObservable(Void aVoid) {
        return exchangeRepository.getExchanges();
    }
}
