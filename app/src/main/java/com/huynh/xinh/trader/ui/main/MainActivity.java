package com.huynh.xinh.trader.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseActivity;
import com.huynh.xinh.trader.ui.market.MarketFragment;
import com.huynh.xinh.trader.ui.splash.SplashActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.img_home_tab_market)
    AppCompatImageView tabMarket;
    @BindView(R.id.img_home_tab_favourite)
    AppCompatImageView tabFavourite;
    @BindView(R.id.img_home_tab_reminded)
    AppCompatImageView tabReminded;
    @BindView(R.id.img_home_tab_setting)
    AppCompatImageView tabSetting;

    @Inject
    MainContract.Presenter presenter;

    private MainFragmentManager mainFragmentManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter.initFragments();
        presenter.preSelectTab();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    public static void start(SplashActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @OnClick({R.id.img_home_tab_market,
            R.id.img_home_tab_favourite,
            R.id.img_home_tab_reminded,
            R.id.img_home_tab_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_home_tab_market:
                presenter.clickTabMarket();
                break;
            case R.id.img_home_tab_favourite:
                presenter.clickTabFavourite();
                break;
            case R.id.img_home_tab_reminded:
                presenter.clickTabReminded();
                break;
            case R.id.img_home_tab_setting:
                presenter.clickTabSetting();
                break;
        }
    }

    @Override
    public void onInitFragments() {
        mainFragmentManager = new MainFragmentManager(getSupportFragmentManager(), R.id.fl_main_content);
    }

    @Override
    public void onPreSelectTab() {
        tabMarket.performClick();
    }

    @Override
    public void onClickTabMarket() {
        setTabSelected(tabMarket);
        mainFragmentManager.showMarketFragment();
    }

    @Override
    public void onClickTabFavourite() {
        setTabSelected(tabFavourite);
        mainFragmentManager.showFavouriteFragment();
    }

    @Override
    public void onClickTabReminded() {
        setTabSelected(tabReminded);
        mainFragmentManager.showRemindedFragment();
    }

    @Override
    public void onClickTabSetting() {
        setTabSelected(tabSetting);
        mainFragmentManager.showSettingFragment();
    }

    private void setTabSelected(AppCompatImageView tab) {
        resetAllTabSelected();
        tab.setSelected(true);
    }

    private void resetAllTabSelected() {
        tabMarket.setSelected(false);
        tabFavourite.setSelected(false);
        tabReminded.setSelected(false);
        tabSetting.setSelected(false);
    }
}
