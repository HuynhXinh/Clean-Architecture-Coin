package com.huynh.xinh.domain.interactor.detialpair;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.domain.repositories.MarketRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDetailPair extends UseCase<Summary, GetDetailPairParam> {

    private final MarketRepository marketRepository;

    @Inject
    public GetDetailPair(ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread,
                         MarketRepository marketRepository) {
        super(threadExecutor, postExecutionThread);
        this.marketRepository = marketRepository;
    }

    @Override
    public Observable<Summary> buildUseCaseObservable(GetDetailPairParam param) {
        return marketRepository.getSummary(param.getMarketName(), param.getPair());
    }
}
