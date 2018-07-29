package com.huynh.xinh.trader.di.modules;

import com.huynh.xinh.trader.ui.detail.DetailPairActivity;
import com.huynh.xinh.trader.ui.detail.module.DetailPairFragmentProvider;
import com.huynh.xinh.trader.ui.main.MainActivity;
import com.huynh.xinh.trader.ui.main.MainActivityModule;
import com.huynh.xinh.trader.ui.market.MarketFragmentProvider;
import com.huynh.xinh.trader.ui.splash.SplashActivity;
import com.huynh.xinh.trader.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {SplashActivityModule.class})
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MarketFragmentProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {
            DetailPairFragmentProvider.class
    })
    abstract DetailPairActivity bindDetailPairActivity();

}