package com.huynh.xinh.domain.interactor.detialpair;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.domain.repositories.MarketRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPeriod extends UseCase<List<Period>, GetPeriodParam> {

    private final MarketRepository marketRepository;

    @Inject
    public GetPeriod(ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread,
                     MarketRepository marketRepository) {
        super(threadExecutor, postExecutionThread);
        this.marketRepository = marketRepository;
    }

    @Override
    public Observable<List<Period>> buildUseCaseObservable(GetPeriodParam param) {
        return marketRepository.getOhlc(param.getMarketName(), param.getPair(), param.getAfter(), param.getPeriods())
                .map(ohlc -> getPeriod(param.getPeriodEnum(), ohlc));
    }

    private List<Period> getPeriod(EnumPeriod periodEnum, Ohlc ohlc) {
        switch (periodEnum) {
            case _1_M: {
                return ohlc.getOneMinute();
            }
            case _3_M: {
                return ohlc.getThreeMinute();
            }
            case _5_M: {
                return ohlc.getFiveMinute();
            }
            case _15_M: {
                return ohlc.getFifteenMinute();
            }
            case _30_M: {
                return ohlc.getThirtyMinute();
            }
            case _1_H: {
                return ohlc.getOneHour();
            }
            case _2_H: {
                return ohlc.getTwoHour();
            }
            case _4_H: {
                return ohlc.getFourHour();
            }
            case _6_H: {
                return ohlc.getSixHour();
            }
            case _12_H: {
                return ohlc.getTwelfthHour();
            }
            case _1_D: {
                return ohlc.getOneDay();
            }
            case _3_D: {
                return ohlc.getThreeDay();
            }
            case _1_W: {
                return ohlc.getOneWeek();
            }
            default: {
                return Collections.emptyList();
            }
        }
    }
}
