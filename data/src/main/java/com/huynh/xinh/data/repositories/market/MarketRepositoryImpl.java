package com.huynh.xinh.data.repositories.market;

import com.annimon.stream.Stream;
import com.huynh.xinh.data.repositories.market.cloud.DetailExchangeDto;
import com.huynh.xinh.data.repositories.market.cloud.GetMarketApi;
import com.huynh.xinh.data.repositories.market.cloud.MarketDto;
import com.huynh.xinh.data.repositories.market.cloud.RouteDto;
import com.huynh.xinh.data.repositories.market.disk.MarketDao;
import com.huynh.xinh.data.repositories.market.responses.ListMarketResponse;
import com.huynh.xinh.domain.models.Market;
import com.huynh.xinh.domain.models.MarketDetail;
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
    public Observable<Boolean> syncMarkets(String routeExchange) {
        return marketApi.getDetailExchange(routeExchange)
                .map(response -> {
                    if (response.getData() != null) {
                        return response.getData();
                    }
                    return new DetailExchangeDto();
                })
                .flatMap((Function<DetailExchangeDto, Observable<ListMarketResponse>>) detailExchangeDto -> {
                    RouteDto routeDto = detailExchangeDto.getRoutes();
                    if (routeDto != null) {
                        String routMarket = detailExchangeDto.getRoutes().getMarkets();
                        return marketApi.getListMarket(routMarket);
                    }
                    return Observable.empty();
                })
                .map((Function<ListMarketResponse, List<MarketDto>>) response -> {
                    if (response.getData() != null && !response.getData().isEmpty()) {
                        return Stream.of(response.getData()).filter(MarketDto::isActive).toList();
                    }
                    return Collections.emptyList();
                })
                .doOnNext(marketDtos -> marketDao.save(MarketMapper.INSTANCE.toMarketEntities(marketDtos)))
                .map(marketDtos -> marketDtos != null && !marketDtos.isEmpty());
    }

    @Override
    public Observable<List<Market>> getMarkets(String exchangeName) {
        return marketDao.getAllMarketByExchangeName(exchangeName).toObservable().map(MarketMapper.INSTANCE::toMarkets);
    }

    @Override
    public Observable<List<Market>> getMarkets(String exchangeName, int page, int maxResult) {
        int offset = page * maxResult;
        return marketDao.getMarkets(exchangeName, offset, maxResult).toObservable().map(MarketMapper.INSTANCE::toMarkets);
    }

    @Override
    public Observable<MarketDetail> getMarketDetail(String marketRoute) {
        return marketApi.getMarketDetail(marketRoute).map(response -> MarketMapper.INSTANCE.toMarketDetail(response.getData()));
    }

    @Override
    public Observable<Summary> getSummary(String summaryUrl) {
        return marketApi.getSummary(summaryUrl).map(response -> MarketMapper.INSTANCE.toSummary(response.getData()));
    }

    @Override
    public Observable<Ohlc> getOhlc(String ohlcUrl, long after, String periods) {
        return marketApi.getOhlc(ohlcUrl, after, periods).map(response -> MarketMapper.INSTANCE.toOhlc(response.getData()));
    }
}
