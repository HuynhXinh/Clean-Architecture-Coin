package com.huynh.xinh.trader.ui.market;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MarketFragmentProvider {
    @ContributesAndroidInjector(modules = MarketFragmentModule.class)
    abstract MarketFragment provideMarketFragmentFactory();
}
