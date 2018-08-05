package com.huynh.xinh.trader.ui.main;

import com.huynh.xinh.trader.base.presenter.BasePresenter;

/**
 * Created by XinhHuynh on 2/27/2018.
 */

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    MainPresenter(MainContract.View mainView) {
        super(mainView);
    }

    @Override
    public void initFragments() {
        getView().onInitFragments();
    }

    @Override
    public void preSelectTab() {
        getView().onPreSelectTab();
    }

    @Override
    public void clickTabMarket() {
        getView().onClickTabMarket();
    }

    @Override
    public void clickTabFavourite() {
        getView().onClickTabFavourite();
    }

    @Override
    public void clickTabReminded() {
        getView().onClickTabReminded();
    }

    @Override
    public void clickTabSetting() {
        getView().onClickTabSetting();
    }
}
