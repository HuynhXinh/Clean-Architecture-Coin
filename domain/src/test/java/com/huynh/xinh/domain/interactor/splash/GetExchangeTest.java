package com.huynh.xinh.domain.interactor.splash;

import com.huynh.xinh.domain.interactor.BaseUseCaseTest;
import com.huynh.xinh.domain.repositories.ExchangeRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetExchangeTest extends BaseUseCaseTest {

    @InjectMocks
    private GetExchange getExchange;

    @Mock
    private ExchangeRepository exchangeRepository;

    @Test
    public void buildUseCaseObservable() {
        getExchange.buildUseCaseObservable(null);

        verify(exchangeRepository).getExchanges();
    }
}