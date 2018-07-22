package com.huynh.xinh.trader.ui.splash;

import com.huynh.xinh.trader.base.presenter.Contract;

public class SplashContract {
    interface View extends Contract.IView {

        void onStartJob();

        void showLoading();

        void hideLoading();

        void showError();

        void gotoMainActivity();

        void onSyncExchangeCoinSuccess();

        void onSyncMarketSuccess();

        void onLoadExchangeSuccess();
    }

    interface Presenter extends Contract.IPresenter<SplashContract.View> {

        void startJob();

        void syncExchangeAndCoin();

        void syncMarkets();

        void loadExchanges();

        void loadCoins();
    }
}
