package com.huynh.xinh.trader.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseActivity;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;

import static com.huynh.xinh.trader.Constants.BUNDLE_DETAIL_PAIR_PRESENTER_MODEL;

public class DetailPairActivity extends BaseActivity {
    public static void start(Context context, DetailPairFragmentParam detailPairParam) {
        Intent intent = new Intent(context, DetailPairActivity.class);
        intent.putExtra(BUNDLE_DETAIL_PAIR_PRESENTER_MODEL, detailPairParam);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_pair;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        DetailPairFragmentParam detailPairParam = (DetailPairFragmentParam) getIntent().getExtras().get(BUNDLE_DETAIL_PAIR_PRESENTER_MODEL);
        DetailPairFragment detailPairFragment = DetailPairFragment.newInstance(detailPairParam);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_detail_pair_content, detailPairFragment)
                .commit();
    }

    @Override
    protected void initEvent() {

    }
}
