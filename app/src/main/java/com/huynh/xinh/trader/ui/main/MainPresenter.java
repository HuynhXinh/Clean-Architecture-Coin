package com.huynh.xinh.trader.ui.main;

import com.huynh.xinh.trader.base.presenter.BasePresenter;

/**
 * Created by XinhHuynh on 2/27/2018.
 */

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    MainPresenter(MainContract.View mainView) {
        super(mainView);
    }
}
