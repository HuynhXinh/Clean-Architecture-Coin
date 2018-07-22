package com.huynh.xinh.trader.ui.splash;

import com.huynh.xinh.domain.interactor.splash.GetCoin;
import com.huynh.xinh.domain.interactor.splash.GetExchange;
import com.huynh.xinh.domain.interactor.splash.SyncExchangeAndCoin;
import com.huynh.xinh.domain.interactor.splash.SyncMarket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {
    @InjectMocks
    private SplashPresenter presenter;

    @Mock
    private SplashContract.View view;

    @Mock
    private SyncExchangeAndCoin syncListExchangeAndCoin;

    @Mock
    private SyncMarket syncMarket;

    @Mock
    private GetExchange getExchange;

    @Mock
    private GetCoin getCoin;

    @Mock
    private SplashPreference splashPreference;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void startJob() {
        presenter.startJob();

        verify(view).onStartJob();
    }

    @Test
    public void syncExchangeAndCoin_unSynced() {
        presenter.syncExchangeAndCoin();

        verify(view).showLoading();
    }

    @Test
    public void syncMarkets() {
    }

    @Test
    public void loadExchanges() {
    }

    @Test
    public void loadCoins() {
    }

    @Test
    public void navigateToMain() {
    }
}