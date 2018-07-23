package com.huynh.xinh.data.repositories.market;

import com.annimon.stream.Stream;
import com.huynh.xinh.data.repositories.market.cloud.GetMarketApi;
import com.huynh.xinh.data.repositories.market.cloud.MarketDto;
import com.huynh.xinh.data.repositories.market.cloud.PairDto;
import com.huynh.xinh.data.repositories.market.disk.MarketDao;
import com.huynh.xinh.data.repositories.market.responses.ListMarketResponse;
import com.huynh.xinh.domain.models.Market;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.domain.repositories.MarketRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MarketRepositoryImpl implements MarketRepository {
    private final GetMarketApi marketApi;
    private final MarketDao marketDao;

    @Inject
    public MarketRepositoryImpl(GetMarketApi marketApi, MarketDao marketDao) {
        this.marketApi = marketApi;
        this.marketDao = marketDao;
    }

    @Override
    public Observable<Boolean> syncMarkets(String marketName) {
        return marketApi.getListMarket(marketName)
                .map((Function<ListMarketResponse, List<MarketDto>>) response -> {
                    List<MarketDto> marketDtos = response.getData();
                    if (marketDtos != null && !marketDtos.isEmpty()) {
                        return Stream.of(marketDtos).filter(MarketDto::isActive).toList();
                    }
                    return Collections.emptyList();
                })
                .flatMap(Observable::fromIterable)
                .flatMap(marketDto -> marketApi.getPairs(marketDto.getPair()).map(response -> {
                    PairDto pairDto = response.getData();
                    if (pairDto != null) {
                        marketDto.setAsset(pairDto.getBase().getSymbol());
                        marketDto.setQuote(pairDto.getQuote().getSymbol());
                    }
                    return marketDto;
                }))
                .toList()
                .toObservable()
                .doOnNext(marketDtos -> marketDao.save(MarketMapper.INSTANCE.toMarketEntities(marketDtos)))
                .map(marketDtos -> marketDtos != null && !marketDtos.isEmpty());
    }

    @Override
    public Observable<List<Market>> getMarkets(String exchangeName, int page, int maxResult) {
        int offset = page * maxResult;
        return marketDao.getMarkets(exchangeName, offset, maxResult).toObservable().map(MarketMapper.INSTANCE::toMarkets);
    }

    @Override
    public Observable<Summary> getSummary(String marketName, String pair) {
        return marketApi.getSummary(marketName, pair)
                .map(response -> MarketMapper.INSTANCE.toSummary(response.getData()));
    }

    @Override
    public Observable<Ohlc> getOhlc(String marketName, String pair, long after, String periods) {
        return marketApi.getOhlc(marketName, pair, after, periods)
                .map(response -> MarketMapper.INSTANCE.toOhlc(response.getData()));
    }
}
