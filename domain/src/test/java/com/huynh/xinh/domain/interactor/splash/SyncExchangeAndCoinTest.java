package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.interactor.BaseUseCaseTest;
import com.huynh.xinh.domain.repositories.CoinRepository;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SyncExchangeAndCoinTest extends BaseUseCaseTest {
    @InjectMocks
    private SyncExchangeAndCoin syncExchangeAndCoin;

    @Mock
    private ExchangeRepository exchangeRepository;

    @Mock
    private CoinRepository coinRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void buildUseCaseObservable() {
        when(exchangeRepository.syncExchanges()).thenReturn(Observable.just(Boolean.TRUE));
        when(coinRepository.syncCoins()).thenReturn(Observable.just(Boolean.TRUE));

        syncExchangeAndCoin.buildUseCaseObservable(null).test();

        verify(exchangeRepository).syncExchanges();
        verify(coinRepository).syncCoins();
    }
}