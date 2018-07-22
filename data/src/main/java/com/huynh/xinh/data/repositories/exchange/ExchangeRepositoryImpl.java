package com.huynh.xinh.data.repositories.exchange;

import com.annimon.stream.Stream;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeApi;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeDto;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeDao;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeEntity;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

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
                .map((Function<ListExchangeResponse, List<ExchangeDto>>) response -> {
                    if (response.getData() != null && !response.getData().isEmpty()) {
                        return response.getData();
                    }
                    throw new SyncExChangeException();
                })
                .doOnNext(exchangeDtos -> {
                    List<ExchangeEntity> exchangeEntities = Stream.of(ExchangeMapper.INSTANCE.toExchangeEntities(exchangeDtos))
                            .filter(ExchangeEntity::isActive)
                            .toList();
                    exchangeDao.save(exchangeEntities);
                })
                .map(exchangeDtos -> exchangeDtos != null && !exchangeDtos.isEmpty());
    }

    @Override
    public Observable<List<Exchange>> getExchanges() {
        return exchangeDao.getAllExchanges().toObservable().map(ExchangeMapper.INSTANCE::toExchanges);
    }
}
