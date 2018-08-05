package com.huynh.xinh.trader.ui.detail.module;


import com.huynh.xinh.domain.interactor.detialpair.GetDetailPair;
import com.huynh.xinh.domain.interactor.detialpair.GetPeriod;
import com.huynh.xinh.trader.ui.detail.DetailPairContract;
import com.huynh.xinh.trader.ui.detail.DetailPairFragment;
import com.huynh.xinh.trader.ui.detail.DetailPairPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailPairFragmentModule {
    @Provides
    DetailPairContract.Presenter providePresenter(DetailPairContract.View view,
                                                  GetDetailPair getDetailPair,
                                                  GetPeriod getPeriod) {
        return new DetailPairPresenter(view, getDetailPair, getPeriod);
    }

    @Provides
    DetailPairContract.View provideView(DetailPairFragment view) {
        return view;
    }
}
