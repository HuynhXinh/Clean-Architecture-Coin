package com.huynh.xinh.domain.interactor.market;

import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.domain.interactor.UseCase;
import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.domain.repositories.MarketRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSummaryMarkets extends UseCase<List<MarketSummary>, GetMarketParam> {
    private static final int MAX_RESULT = 3;

    private final MarketRepository marketRepository;

    @Inject
    public GetSummaryMarkets(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             MarketRepository marketRepository) {
        super(threadExecutor, postExecutionThread);
        this.marketRepository = marketRepository;
    }

    @Override
    public Observable<List<MarketSummary>> buildUseCaseObservable(GetMarketParam param) {
        String exchangeName = param.getExchangeName();
        int page = param.getPage();

        return marketRepository.getMarkets(exchangeName, page, MAX_RESULT)
                .flatMapIterable(markets -> markets)
                .flatMap(market -> marketRepository.getMarketDetail(market.getRoute()))
                .flatMap(marketDetail -> {
                    String summaryUrl = marketDetail.getRoutes().getSummary();
                    String ohlcUrl = marketDetail.getRoutes().getOhlc();
                    return Observable.zip(
                            marketRepository.getSummary(summaryUrl),
                            marketRepository.getOhlc(ohlcUrl, param.getAfter(), param.getPeriods()),
                            (summary, ohlc) ->
                                    MarketSummaryMapper.builder()
                                            .marketDetail(marketDetail)
                                            .summary(summary)
                                            .ohlc(ohlc)
                                            .build()
                                            .getMarketSummary());
                })
                .toList().toObservable();

    }
}
