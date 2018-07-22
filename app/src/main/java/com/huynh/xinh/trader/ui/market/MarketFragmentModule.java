package com.huynh.xinh.trader.ui.market;


import com.huynh.xinh.domain.interactor.market.GetSummaryMarkets;

import dagger.Module;
import dagger.Provides;

@Module
public class MarketFragmentModule {
    @Provides
    MarketContract.Presenter providePresenter(MarketContract.View view,
                                              GetSummaryMarkets getMarket) {
        return new MarketPresenter(view, getMarket);
    }

    @Provides
    MarketContract.View provideView(MarketFragment view) {
        return view;
    }
}
