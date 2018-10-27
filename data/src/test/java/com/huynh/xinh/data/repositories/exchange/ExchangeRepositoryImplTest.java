package com.huynh.xinh.data.repositories.exchange;

import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeApi;
import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeDto;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeDao;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRepositoryImplTest {

    @InjectMocks
    private ExchangeRepositoryImpl repository;

    @Mock
    private ExchangeApi exchangeApi;

    @Mock
    private ExchangeDao exchangeDao;

    @Captor
    private ArgumentCaptor<List<ExchangeEntity>> argumentCaptorExchanges;

    @Test
    public void syncExchanges_emptyException() {
        when(exchangeApi.getListExchangeMarket()).thenReturn(mockEmptyExchangeResponse());

        repository.syncExchanges().test().assertError(SyncExchangeEmptyException.class);

        verifyNoMoreInteractions(exchangeDao);
    }

    private Observable<ListExchangeResponse> mockEmptyExchangeResponse() {
        ListExchangeResponse response = new ListExchangeResponse();
        return Observable.just(response);
    }

    @Test
    public void syncExchanges_success() {
        when(exchangeApi.getListExchangeMarket()).thenReturn(mockExchangeResponse());

        repository.syncExchanges().test();

        verify(exchangeDao).save(argumentCaptorExchanges.capture());
        List<ExchangeEntity> actualExchangeSaveds = argumentCaptorExchanges.getValue();
        assertEquals("Bittrex", actualExchangeSaveds.get(0).getSymbol());
        assertEquals(1, actualExchangeSaveds.size());

        verify(exchangeDao).deleteAllExchangeNotSupport(anyList());
    }

    private Observable<ListExchangeResponse> mockExchangeResponse() {
        ListExchangeResponse response = new ListExchangeResponse();

        ExchangeDto exchangeDtoActive = new ExchangeDto();
        exchangeDtoActive.setSymbol("Bittrex");
        exchangeDtoActive.setActive(true);

        ExchangeDto exchangeDtoNotActive = new ExchangeDto();
        exchangeDtoNotActive.setSymbol("Binance");
        exchangeDtoNotActive.setActive(false);

        response.setData(Arrays.asList(exchangeDtoActive, exchangeDtoNotActive));

        return Observable.just(response);
    }

    @Test
    public void getExchanges() {
        when(exchangeDao.getAllExchanges()).thenReturn(Maybe.just(Collections.emptyList()));

        repository.getExchanges();

        verify(exchangeDao).getAllExchanges();
    }
}