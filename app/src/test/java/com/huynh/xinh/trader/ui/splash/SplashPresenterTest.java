package com.huynh.xinh.trader.ui.splash;

import com.huynh.xinh.domain.interactor.OutputObserver;
import com.huynh.xinh.domain.interactor.splash.GetCoin;
import com.huynh.xinh.domain.interactor.splash.GetExchange;
import com.huynh.xinh.domain.interactor.splash.SyncExchangeAndCoin;
import com.huynh.xinh.domain.interactor.splash.SyncMarket;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.trader.ui.CoinManager;
import com.huynh.xinh.trader.ui.ExchangeManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({CoinManager.class, ExchangeManager.class})
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

    @Captor
    private ArgumentCaptor<OutputObserver<Boolean>> argumentCaptorBoolean;

    @Captor
    private ArgumentCaptor<OutputObserver<List<Exchange>>> argumentCaptorExchanges;

    @Captor
    private ArgumentCaptor<OutputObserver<List<Coin>>> argumentCaptorCoins;

    @Mock
    private ExchangeManager exchangeManager;

    @Mock
    private CoinManager coinManager;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(ExchangeManager.class);
        PowerMockito.mockStatic(CoinManager.class);

        when(ExchangeManager.getInstance()).thenReturn(exchangeManager);
        when(CoinManager.getInstance()).thenReturn(coinManager);
    }

    @Test
    public void startJob() {
        presenter.startJob();

        verify(view).onStartJob();
        assertEquals(1, 1);
    }

    @Test
    public void syncExchangeAndCoin_noSync() {
        when(splashPreference.isExchangeCoinSynced()).thenReturn(TRUE);
        presenter.syncExchangeAndCoin();

        verify(view).showLoading();
        verify(view).onSyncExchangeCoinSuccess();
    }

    @Test
    public void syncExchangeAndCoin_sync_error() {
        when(splashPreference.isExchangeCoinSynced()).thenReturn(FALSE);
        presenter.syncExchangeAndCoin();

        verify(syncListExchangeAndCoin).execute(argumentCaptorBoolean.capture());
        argumentCaptorBoolean.getValue().onError(new Throwable());

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void syncExchangeAndCoin_sync_success_false() {
        when(splashPreference.isExchangeCoinSynced()).thenReturn(FALSE);
        presenter.syncExchangeAndCoin();

        verify(syncListExchangeAndCoin).execute(argumentCaptorBoolean.capture());
        argumentCaptorBoolean.getValue().onNext(FALSE);

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void syncExchangeAndCoin_sync_success_true() {
        when(splashPreference.isExchangeCoinSynced()).thenReturn(FALSE);
        presenter.syncExchangeAndCoin();

        verify(syncListExchangeAndCoin).execute(argumentCaptorBoolean.capture());
        argumentCaptorBoolean.getValue().onNext(TRUE);

        verify(view).showLoading();
        verify(view).onSyncExchangeCoinSuccess();
        verify(splashPreference).setExchangeCoinSynced(TRUE);
    }

    @Test
    public void syncMarkets_noSync() {
        when(splashPreference.isMarketSynced()).thenReturn(TRUE);

        presenter.syncMarkets();

        verify(view).onSyncMarketSuccess();
    }

    @Test
    public void syncMarkets_sync_error() {
        when(splashPreference.isMarketSynced()).thenReturn(FALSE);

        presenter.syncMarkets();

        verify(syncMarket).execute(argumentCaptorBoolean.capture());
        argumentCaptorBoolean.getValue().onError(new Throwable());

        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void syncMarkets_sync_complete() {
        when(splashPreference.isMarketSynced()).thenReturn(FALSE);

        presenter.syncMarkets();

        verify(syncMarket).execute(argumentCaptorBoolean.capture());

        argumentCaptorBoolean.getValue().onComplete();

        verify(view).onSyncMarketSuccess();
    }

    @Test
    public void loadExchanges_error() {
        presenter.loadExchanges();

        verify(getExchange).execute(argumentCaptorExchanges.capture());

        argumentCaptorExchanges.getValue().onError(new Throwable());

        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void loadExchanges_success_empty() {
        presenter.loadExchanges();

        verify(getExchange).execute(argumentCaptorExchanges.capture());

        argumentCaptorExchanges.getValue().onNext(Collections.emptyList());

        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void loadExchanges_success() {
        presenter.loadExchanges();

        verify(getExchange).execute(argumentCaptorExchanges.capture());

        argumentCaptorExchanges.getValue().onNext(Collections.singletonList(new Exchange()));

        verify(exchangeManager).set(anyList());
        verify(view).onLoadExchangeSuccess();
    }

    @Test
    public void loadCoins_error() {
        presenter.loadCoins();

        verify(getCoin).execute(argumentCaptorCoins.capture());

        argumentCaptorCoins.getValue().onError(new Throwable());

        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void loadCoins_success_empty() {
        presenter.loadCoins();

        verify(getCoin).execute(argumentCaptorCoins.capture());

        argumentCaptorCoins.getValue().onNext(Collections.emptyList());

        verify(view).hideLoading();
        verify(view).showError();
    }

    @Test
    public void loadCoins_success() {
        presenter.loadCoins();

        verify(getCoin).execute(argumentCaptorCoins.capture());

        argumentCaptorCoins.getValue().onNext(Collections.singletonList(new Coin()));

        verify(coinManager).set(anyList());
        verify(view).gotoMainActivity();
    }
}