package com.huynh.xinh.trader.ui.main;

import android.support.v4.app.FragmentManager;

import com.huynh.xinh.trader.base.ui.BaseFragment;
import com.huynh.xinh.trader.ui.market.MarketFragment;

public class MainFragmentManager {
    private FragmentManager supportFragmentManager;
    private int flMainContent;

    private MarketFragment marketFragment;

    public MainFragmentManager(FragmentManager supportFragmentManager, int flMainContent) {
        this.supportFragmentManager = supportFragmentManager;
        this.flMainContent = flMainContent;
    }


    public void showMarketFragment() {
        hideAllFragment();

        if (marketFragment == null) {
            marketFragment = MarketFragment.newInstance();

            addFragment(marketFragment, MarketFragment.TAG);
        }

        showFragment(marketFragment);
    }

    private void hideAllFragment() {
        hideFragment(marketFragment);
    }

    public void showFavouriteFragment() {
        // TODO: 7/22/2018
    }

    public void showRemindedFragment() {
        // TODO: 7/22/2018
    }

    public void showSettingFragment() {
        // TODO: 7/22/2018
    }

    private void hideFragment(BaseFragment fragment) {
        if (fragment == null) {
            return;
        }
        supportFragmentManager.beginTransaction()
                .hide(fragment)
                .commit();
    }

    private void addFragment(BaseFragment fragment, String tag) {
        supportFragmentManager.beginTransaction()
                .add(flMainContent, fragment, tag)
                .commit();
    }

    private void showFragment(BaseFragment fragment) {
        supportFragmentManager.beginTransaction()
                .show(fragment)
                .commit();
    }
}
