package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.trader.base.presenter.Contract;

class DetailPairContract {
    interface View extends Contract.IView {
        void showDetailPair(DetailPairViewModel detailPairViewModel);

        void showLoading();

        void showError();
    }

    interface Presenter extends Contract.IPresenter<DetailPairContract.View> {
        void getDetailPair(String market, String pair);
    }
}
