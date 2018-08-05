package com.huynh.xinh.trader.ui.main;

import com.huynh.xinh.trader.base.presenter.Contract;

/**
 * Created by XinhHuynh on 2/27/2018.
 */

public interface MainContract {
    interface View extends Contract.IView {

        void onInitFragments();

        void onPreSelectTab();

        void onClickTabMarket();

        void onClickTabFavourite();

        void onClickTabReminded();

        void onClickTabSetting();
    }

    interface Presenter extends Contract.IPresenter<View> {

        void initFragments();

        void preSelectTab();

        void clickTabMarket();

        void clickTabFavourite();

        void clickTabReminded();

        void clickTabSetting();
    }
}
