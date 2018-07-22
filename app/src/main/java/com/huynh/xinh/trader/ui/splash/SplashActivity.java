package com.huynh.xinh.trader.ui.splash;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.evernote.android.job.JobManager;
import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseActivity;
import com.huynh.xinh.trader.common.SyncCommonScheduleJob;
import com.huynh.xinh.trader.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    @BindView(R.id.pb_all_loading)
    ProgressBar pbLoading;

    @Inject
    SplashContract.Presenter presenter;

    @Inject
    JobManager jobManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        presenter.startJob();
        presenter.syncExchangeAndCoin();
    }

    @Override
    public void onSyncExchangeCoinSuccess() {
        presenter.syncMarkets();
    }

    @Override
    public void onSyncMarketSuccess() {
        presenter.loadExchanges();
    }

    @Override
    public void onLoadExchangeSuccess() {
        presenter.loadCoins();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void gotoMainActivity() {
        MainActivity.start(this);
        finish();
    }

    @Override
    public void onStartJob() {
        jobManager.schedule(SyncCommonScheduleJob.schedulePeriodicJob());
    }

    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
