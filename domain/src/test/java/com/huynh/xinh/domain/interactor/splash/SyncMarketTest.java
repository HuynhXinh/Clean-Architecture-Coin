package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.interactor.BaseUseCaseTest;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.domain.repositories.ExchangeRepository;
import com.huynh.xinh.domain.repositories.MarketRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SyncMarketTest extends BaseUseCaseTest {

    @InjectMocks
    private SyncMarket syncMarket;

    @Mock
    private ExchangeRepository exchangeRepository;

    @Mock
    private MarketRepository marketRepository;

    @Test
    public void buildUseCaseObservable() {
        when(exchangeRepository.getExchanges()).thenReturn(Observable.just(Collections.singletonList(new Exchange())));

        syncMarket.buildUseCaseObservable(null).test();

        verify(exchangeRepository).getExchanges();
        verify(marketRepository).syncMarkets(any());
    }
}