package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.interactor.BaseUseCaseTest;
import com.huynh.xinh.domain.repositories.CoinRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetCoinTest extends BaseUseCaseTest {

    @InjectMocks
    private GetCoin getCoin;

    @Mock
    private CoinRepository coinRepository;

    @Test
    public void buildUseCaseObservable() {
        getCoin.buildUseCaseObservable(null);

        verify(coinRepository).getCoins();
    }
}