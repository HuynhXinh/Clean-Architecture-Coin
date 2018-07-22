package com.huynh.xinh.trader.ui.main;

import android.content.Intent;
import android.os.Bundle;

import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseActivity;
import com.huynh.xinh.trader.ui.market.MarketFragment;
import com.huynh.xinh.trader.ui.splash.SplashActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main_content, MarketFragment.newInstance())
                .commit();
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
}
