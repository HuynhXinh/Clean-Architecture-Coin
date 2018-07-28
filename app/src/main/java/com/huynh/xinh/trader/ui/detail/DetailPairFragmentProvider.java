package com.huynh.xinh.trader.ui.detail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailPairFragmentProvider {
    @ContributesAndroidInjector(modules = DetailPairFragmentModule.class)
    abstract DetailPairFragment provideDetailPairFragmentFactory();
}
