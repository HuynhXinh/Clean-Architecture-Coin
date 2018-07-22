package com.huynh.xinh.data.repositories.coin;

import com.huynh.xinh.data.repositories.coin.cloud.CoinDto;
import com.huynh.xinh.data.repositories.coin.cloud.GetCoinApi;
import com.huynh.xinh.data.repositories.coin.disk.CoinDao;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by XinhHuynh on 2/28/2018.
 */

public class CoinRepositoryImpl implements CoinRepository {

    private final GetCoinApi getCoinApi;
    private final CoinDao coinDao;

    @Inject
    public CoinRepositoryImpl(GetCoinApi getCoinApi, CoinDao coinDao) {
        this.getCoinApi = getCoinApi;
        this.coinDao = coinDao;
    }

    @Override
    public Observable<Boolean> syncCoins() {
        return getCoinApi.getListCoin()
                .map((Function<ListCoinResponse, List<CoinDto>>) response -> {
                    if (response.getData() != null && !response.getData().isEmpty()) {
                        return response.getData();
                    }
                    throw new SyncCoinException();
                })
                .doOnNext(coinDtos -> {
                    coinDao.save(CoinMapper.INSTANCE.toCoinEntities(coinDtos));
                })
                .map(coinDtos -> coinDtos != null && !coinDtos.isEmpty());
    }

    @Override
    public Observable<List<Coin>> getCoins() {
        return coinDao.getAllCoins().toObservable().map(CoinMapper.INSTANCE::toCoins);
    }
}
