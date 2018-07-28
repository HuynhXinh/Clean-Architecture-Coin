package com.huynh.xinh.trader.ui.detail;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import lt.neworld.spanner.Spanner;
import lt.neworld.spanner.Spans;

import static com.huynh.xinh.trader.Constants.BUNDLE_DETAIL_PAIR_PRESENTER_MODEL;

public class DetailPairFragment extends BaseFragment implements DetailPairContract.View {
    @BindView(R.id.tv_fragment_detail_pair_title)
    TextView tvTitlePair;
    @BindView(R.id.tv_fragment_detail_pair_last_price)
    TextView tvLastPrice;
    @BindView(R.id.tv_fragment_detail_pair_price_change)
    TextView tvChange;
    @BindView(R.id.tv_fragment_detail_pair_price_high)
    TextView tvPriceHigh;
    @BindView(R.id.tv_fragment_detail_pair_price_low)
    TextView tvPriceLow;
    @BindView(R.id.candle_chart_fragment_detail_pair)
    CandleStickChart chart;

    @Inject
    DetailPairContract.Presenter presenter;

    private DetailPairParam detailPairParam;

    public static DetailPairFragment newInstance(DetailPairParam detailPairPresenterModel) {
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_DETAIL_PAIR_PRESENTER_MODEL, detailPairPresenterModel);
        DetailPairFragment fragment = new DetailPairFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_detail_pair;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailPairParam = (DetailPairParam) getArguments().get(BUNDLE_DETAIL_PAIR_PRESENTER_MODEL);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle bundle) {
        tvTitlePair.setText(getTitle());

        presenter.getDetailPair(detailPairParam.getMarketSymbol(), detailPairParam.getPair());
    }

    private Spannable getTitle() {
        String asset = detailPairParam.getAsset().toUpperCase();
        String quote = detailPairParam.getQuote().toUpperCase();
        return new Spanner()
                .append(asset, Spans.foreground(Color.BLACK))
                .append("/")
                .append(quote, Spans.foreground(Color.parseColor("#808080")));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @OnClick({R.id.img_fragment_detail_pair_btn_back, R.id.img_fragment_detail_pair_btn_favourite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fragment_detail_pair_btn_back:
                getActivity().onBackPressed();
                break;
            case R.id.img_fragment_detail_pair_btn_favourite:
                break;
        }
    }

    @Override
    public void showDetailPair(DetailPairViewModel detailPairViewModel) {
        tvLastPrice.setText(detailPairViewModel.getLastPrice());
        tvChange.setText(detailPairViewModel.getChange());
        tvChange.setTextColor(detailPairViewModel.getColorChange());
        tvPriceHigh.setText(detailPairViewModel.getHigh());
        tvPriceLow.setText(detailPairViewModel.getLow());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }
}
