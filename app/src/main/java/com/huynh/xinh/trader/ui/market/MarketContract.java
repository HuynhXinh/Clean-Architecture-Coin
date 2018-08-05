package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.models.Exchange;
import com.huynh.xinh.trader.base.presenter.Contract;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;

import java.util.List;

class MarketContract {
    interface View extends Contract.IView {
        void onShowExchange(Exchange exchange);

        void showLoading();

        void render(List<ItemMarketViewModel> marketViewModels);

        void renderMore(List<ItemMarketViewModel> marketViewModels);

        void showNoData();

        void showErrorLoadMore();

        void hideLoadMore();

        void startDetailPairActivity(DetailPairFragmentParam detailPairParam);
    }

    interface Presenter extends Contract.IPresenter<MarketContract.View> {

        void setPeriodAfter(long periodAfter);

        void loadExchanges();

        void refresh();

        void loadMore();

        void onItemClick(ItemMarketViewModel marketViewModel);
    }
}
