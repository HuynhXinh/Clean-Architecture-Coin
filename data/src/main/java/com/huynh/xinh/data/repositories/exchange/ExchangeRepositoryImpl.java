package com.huynh.xinh.data.repositories.exchange;

import com.annimon.stream.Stream;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeApi;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeDto;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeDao;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ExchangeRepositoryImpl implements ExchangeRepository {
    private final ExchangeApi exchangeApi;
    private final ExchangeDao exchangeDao;

    @Inject
    public ExchangeRepositoryImpl(ExchangeApi exchangeApi, ExchangeDao exchangeDao) {
        this.exchangeApi = exchangeApi;
        this.exchangeDao = exchangeDao;
    }

    @Override
    public Observable<Boolean> syncExchanges() {
        return exchangeApi.getListExchangeMarket()
                .map(response -> {
                    List<ExchangeDto> exchangeDtos = response.getData();
                    if (exchangeDtos != null && !exchangeDtos.isEmpty()) {
                        return exchangeDtos;
                    }
                    throw new SyncExChangeException();
                })
                .doOnNext(exchangeDtos -> {
                    List<ExchangeDto> exchangeSupports = Stream.of(exchangeDtos)
                            .filter(value -> value.isActive() && isExchangeSupported(value.getSymbol()))
                            .toList();

                    exchangeDao.save(ExchangeMapper.INSTANCE.toExchangeEntities(exchangeSupports));
                    List<Long> exchangeSupportIds = Stream.of(exchangeSupports).map(ExchangeDto::getId).toList();
                    exchangeDao.delete(exchangeSupportIds);
                })
                .map(exchangeDtos -> exchangeDtos != null && !exchangeDtos.isEmpty());
    }

    @Override
    public Observable<List<Exchange>> getExchanges() {
        return exchangeDao.getAllExchanges().toObservable().map(ExchangeMapper.INSTANCE::toExchanges);
    }

    private boolean isExchangeSupported(String exchange) {
        return Stream.of(exchangeSupports()).anyMatch(exchange::equalsIgnoreCase);
    }

    private List<String> exchangeSupports() {
        return Arrays.asList(
                "Bittrex",
                "Binance",
                "Bitfinex",
                "Kraken"
        );
    }
}
