package com.huynh.xinh.trader.ui.detail;


import com.huynh.xinh.domain.interactor.detialpair.GetDetailPair;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailPairFragmentModule {
    @Provides
    DetailPairContract.Presenter providePresenter(DetailPairContract.View view,
                                                  GetDetailPair getDetailPair) {
        return new DetailPairPresenter(view, getDetailPair);
    }

    @Provides
    DetailPairContract.View provideView(DetailPairFragment view) {
        return view;
    }
}
