package com.huynh.xinh.trader.ui.detail;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.huynh.xinh.data.utils.DateTimeUtils;
import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.base.ui.BaseFragment;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;
import com.huynh.xinh.trader.ui.detail.model.DetailPairViewModel;
import com.huynh.xinh.trader.ui.detail.model.ItemTabPeriodTimeViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    @BindView(R.id.ll_fragment_detail_pair_tab_period_time)
    LinearLayout llTabPeriodTime;
    @BindView(R.id.candle_chart_fragment_detail_pair)
    CandleStickChart chart;

    @Inject
    DetailPairContract.Presenter presenter;

    public static DetailPairFragment newInstance(DetailPairFragmentParam detailPairPresenterModel) {
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
        presenter.initTabPeriodTimeView();
        initChart();
    }

    private void initChart() {
        chart.setBackgroundColor(Color.WHITE);

        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        chart.fitScreen();
        chart.moveViewToX(10);

        chart.getXAxis().setEnabled(false);

        chart.getAxisLeft().setEnabled(false);

        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setEnabled(false);
    }

    @Override
    public void onInitTabPeriodTime(List<ItemTabPeriodTimeViewModel> itemTabPeriodTimeViewModels) {
        addItemTabPeriods(itemTabPeriodTimeViewModels);
    }

    private void addItemTabPeriods(List<ItemTabPeriodTimeViewModel> itemTabPeriodTimeViewModels) {
        llTabPeriodTime.removeAllViews();
        for (ItemTabPeriodTimeViewModel item : itemTabPeriodTimeViewModels) {
            LinearLayout itemTabPeriod = (LinearLayout) getLayoutInflater().inflate(R.layout.item_fragment_detail_pair_tab_period, null);
            TextView tvName = itemTabPeriod.findViewById(R.id.tv_item_tab_period_name);

            String tabName = item.getName();

            itemTabPeriod.setTag(tabName);
            tvName.setText(tabName);

            itemTabPeriod.setOnClickListener(view -> {
                setSelectedItemTabPeriod(tabName);
                EnumPeriod enumPeriod = item.getEnumPeriod();
                presenter.getPeriod(enumPeriod, getPeriodAfter(enumPeriod), item.getValue());
            });
            llTabPeriodTime.addView(itemTabPeriod);
        }
    }

    private long getPeriodAfter(EnumPeriod enumPeriod) {
        Calendar cal = Calendar.getInstance();
        switch (enumPeriod) {
            case _1_M: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
                break;
            }
            case _3_M: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 2);
                break;
            }
            case _5_M: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 3);
                break;
            }
            case _15_M: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 6);
                break;
            }
            case _30_M: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 11);
                break;
            }
            case _1_H: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 21);
                break;
            }
            case _2_H: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 42);
                break;
            }
            case _4_H: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 83);
                break;
            }
            case _6_H: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 123);
                break;
            }
            case _12_H: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 248);
                break;
            }
            case _1_D: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 496);
                break;
            }
            case _3_D: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1493);
                break;
            }
            case _1_W: {
                cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1753);
                break;
            }
        }
        return DateTimeUtils.toUnixTimestamp(cal.getTimeInMillis());
    }

    private void setSelectedItemTabPeriod(String tabName) {
        for (int i = 0; i < llTabPeriodTime.getChildCount(); i++) {
            View tabView = llTabPeriodTime.getChildAt(i);
            TextView tvName = tabView.findViewById(R.id.tv_item_tab_period_name);
            ImageView imgIndicator = tabView.findViewById(R.id.img_item_tab_period_indicator);

            if (tabName.equals(tabView.getTag())) {
                tvName.setSelected(true);
                imgIndicator.setSelected(true);
            } else {
                tvName.setSelected(false);
                imgIndicator.setSelected(false);
            }
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        DetailPairFragmentParam detailPairParam = (DetailPairFragmentParam) getArguments().get(BUNDLE_DETAIL_PAIR_PRESENTER_MODEL);
        presenter.initData(detailPairParam);
        presenter.showTitle();
        presenter.getDetailPair();
        presenter.firstSelectedTabPeriod();
    }

    @Override
    public void onShowTitle(String asset, String quote) {
        Spannable spTitle = new Spanner()
                .append(asset.toUpperCase(), Spans.foreground(Color.BLACK))
                .append("/")
                .append(quote.toUpperCase(), Spans.foreground(Color.parseColor("#808080")));
        tvTitlePair.setText(spTitle);
    }

    @Override
    public void showDetailPair(DetailPairViewModel detailPairViewModel) {
        tvLastPrice.setText(detailPairViewModel.getLastPrice());
        tvChange.setText(detailPairViewModel.getChange());
        tvChange.setTextColor(detailPairViewModel.getColorChange());
        tvPriceHigh.setText(getPriceHigh(detailPairViewModel.getHigh()));
        tvPriceLow.setText(getPriceLow(detailPairViewModel.getLow()));
    }

    @Override
    public void onFirstSelectedTabPeriod() {
        // Selected tab 1 Day
        llTabPeriodTime.getChildAt(5).performClick();
    }

    @Override
    public void showChartPeriod(List<Period> periods) {
        ArrayList<CandleEntry> yValue = new ArrayList<>();

        for (int x = 0; x < periods.size(); x++) {
            Period period = periods.get(x);

            float high = period.getHighPrice().floatValue();
            float low = period.getLowPrice().floatValue();

            float open = period.getOpenPrice().floatValue();
            float close = period.getClosePrice().floatValue();

            yValue.add(new CandleEntry(x, high, low, open, close));
        }

        CandleDataSet dataSet = new CandleDataSet(yValue, "");

        dataSet.setDrawIcons(false);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setShadowColor(Color.DKGRAY);
        dataSet.setShadowWidth(0.7f);
        dataSet.setDecreasingColor(Color.RED);
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        dataSet.setIncreasingColor(Color.rgb(122, 242, 84));
        dataSet.setIncreasingPaintStyle(Paint.Style.STROKE);
        dataSet.setNeutralColor(Color.BLUE);

        CandleData data = new CandleData(dataSet);

        chart.setData(data);
        chart.invalidate();
    }

    public Spannable getPriceHigh(String high) {
        return new Spanner()
                .append(getString(R.string.detail_pair_fragment_hight))
                .append(" ")
                .append(high, Spans.foreground(Color.BLACK));
    }

    public Spannable getPriceLow(String low) {
        return new Spanner()
                .append(getString(R.string.detail_pair_fragment_low))
                .append(" ")
                .append(low, Spans.foreground(Color.BLACK));
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
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

}
