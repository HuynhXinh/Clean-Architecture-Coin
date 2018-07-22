package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.trader.base.presenter.Contract;

import java.util.List;

public class MarketContract {
    interface View extends Contract.IView {
        void onShowExchange(Exchange exchange);

        void showLoading();

        void render(List<MarketViewModel> marketViewModels);

        void renderMore(List<MarketViewModel> marketViewModels);

        void showNoData();

        void showErrorLoadMore();

        void hideLoadMore();
    }

    interface Presenter extends Contract.IPresenter<MarketContract.View> {
        void loadExchanges();

        void refresh();

        void loadMore();
    }
}
