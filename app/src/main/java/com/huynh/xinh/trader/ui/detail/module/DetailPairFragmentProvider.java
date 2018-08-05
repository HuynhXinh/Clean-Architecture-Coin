package com.huynh.xinh.trader.ui.detail.module;

import com.huynh.xinh.trader.ui.detail.DetailPairFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DetailPairFragmentProvider {
    @ContributesAndroidInjector(modules = DetailPairFragmentModule.class)
    abstract DetailPairFragment provideDetailPairFragmentFactory();
}
