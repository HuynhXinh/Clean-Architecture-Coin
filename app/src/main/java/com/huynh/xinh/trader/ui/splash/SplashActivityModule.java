package com.huynh.xinh.trader.ui.splash;

import com.evernote.android.job.JobManager;
import com.huynh.xinh.domain.interactor.splash.GetCoin;
import com.huynh.xinh.domain.interactor.splash.GetExchange;
import com.huynh.xinh.domain.interactor.splash.SyncExchangeAndCoin;
import com.huynh.xinh.domain.interactor.splash.SyncMarket;

import dagger.Module;
import dagger.Provides;

/**
 * Created by XinhHuynh on 2/27/2018.
 */

@Module
public class SplashActivityModule {

    @Provides
    SplashContract.Presenter providePresenter(SplashContract.View view,
                                              SyncExchangeAndCoin syncListExchangeAndCoin,
                                              SyncMarket syncMarket,
                                              GetExchange getExchange,
                                              GetCoin getCoin,
                                              SplashPreference splashPreference) {
        return new SplashPresenter(view, syncListExchangeAndCoin, syncMarket, getExchange, getCoin, splashPreference);
    }

    @Provides
    SplashContract.View provideView(SplashActivity view) {
        return view;
    }

}
