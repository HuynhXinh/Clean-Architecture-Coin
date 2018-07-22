package com.huynh.xinh.trader.ui.splash;

import com.huynh.xinh.domain.interactor.OutputObserver;
import com.huynh.xinh.domain.interactor.splash.GetCoin;
import com.huynh.xinh.domain.interactor.splash.GetExchange;
import com.huynh.xinh.domain.interactor.splash.SyncExchangeAndCoin;
import com.huynh.xinh.domain.interactor.splash.SyncMarket;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.trader.base.presenter.BasePresenter;
import com.huynh.xinh.trader.ui.CoinManager;
import com.huynh.xinh.trader.ui.ExchangeManager;
import com.huynh.xinh.trader.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private final SyncExchangeAndCoin syncListExchangeAndCoin;
    private final SyncMarket syncMarket;
    private final GetExchange getExchange;
    private final GetCoin getCoin;
    private final SplashPreference splashPreference;

    @Inject
    SplashPresenter(SplashContract.View view,
                    SyncExchangeAndCoin syncListExchangeAndCoin,
                    SyncMarket syncMarket,
                    GetExchange getExchange,
                    GetCoin getCoin,
                    SplashPreference splashPreference) {

        super(view, syncListExchangeAndCoin, syncMarket);
        this.syncListExchangeAndCoin = syncListExchangeAndCoin;
        this.syncMarket = syncMarket;
        this.getExchange = getExchange;
        this.getCoin = getCoin;
        this.splashPreference = splashPreference;
    }

    @Override
    public void startJob() {
        getView().onStartJob();
    }

    @Override
    public void syncExchangeAndCoin() {
        getView().showLoading();

        if (splashPreference.isExchangeCoinSynced()) {
            getView().onSyncExchangeCoinSuccess();
        } else {
            syncListExchangeAndCoin.execute(new SyncExchangeAndCoinResult());
        }
    }

    private class SyncExchangeAndCoinResult extends OutputObserver<Boolean> {
        @Override
        public void onNext(Boolean isSuccess) {
            super.onNext(isSuccess);
            if (isSuccess) {
                getView().onSyncExchangeCoinSuccess();
                splashPreference.setExchangeCoinSynced(Boolean.TRUE);
            } else {
                getView().showError();
            }
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().showError();
        }
    }

    @Override
    public void syncMarkets() {
        if (splashPreference.isMarketSynced()) {
            getView().onSyncMarketSuccess();
        } else {
            syncMarket.execute(new SyncMarketResult());
        }
    }

    private class SyncMarketResult extends OutputObserver<Boolean> {
        @Override
        public void onNext(Boolean success) {
            super.onNext(success);
            splashPreference.setMarketSynced(Boolean.TRUE);
            getView().onSyncMarketSuccess();
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().showError();
        }
    }

    @Override
    public void loadExchanges() {
        getExchange.execute(new LoadExchangeResult());
    }

    private class LoadExchangeResult extends OutputObserver<List<Exchange>> {
        @Override
        public void onNext(List<Exchange> exchanges) {
            super.onNext(exchanges);
            if (!CommonUtils.isListEmpty(exchanges)) {
                ExchangeManager.getInstance().set(exchanges);
                getView().onLoadExchangeSuccess();
            } else {
                getView().showError();
            }
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().showError();
        }
    }

    @Override
    public void loadCoins() {
        getCoin.execute(new LoadCoinResult());
    }

    private class LoadCoinResult extends OutputObserver<List<Coin>> {
        @Override
        public void onNext(List<Coin> coins) {
            super.onNext(coins);
            if (!CommonUtils.isListEmpty(coins)) {
                CoinManager.getInstance().set(coins);
                navigateToMain();
            } else {
                getView().showError();
            }
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().showError();
        }
    }

    synchronized public void navigateToMain() {
        if (!CommonUtils.isListEmpty(ExchangeManager.getInstance().getExchanges()) &&
                !CommonUtils.isListEmpty(CoinManager.getInstance().getCoins())) {
            getView().gotoMainActivity();
        }
    }
}
