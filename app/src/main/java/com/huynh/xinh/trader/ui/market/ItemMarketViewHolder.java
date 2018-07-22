package com.huynh.xinh.trader.ui.market;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.trader.R;
import com.huynh.xinh.trader.TraderApplication;
import com.huynh.xinh.trader.utils.CommonUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemMarketViewHolder extends BaseViewHolder<MarketViewModel> {

    @BindView(R.id.img_market_icon)
    AppCompatImageView icon;
    @BindView(R.id.tv_market_asset)
    TextView tvAsset;
    @BindView(R.id.tv_market_quote)
    TextView tvQuote;
    @BindView(R.id.tv_market_price)
    TextView tvPrice;
    @BindView(R.id.tv_market_percent)
    TextView tvPercent;
    @BindView(R.id.img_market_favorite)
    AppCompatImageView imgFavorite;
    @BindView(R.id.chart_item_market)
    LineChart chart;

    public ItemMarketViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_market);
        ButterKnife.bind(this, itemView);

        initChart();
    }

    @Override
    public void setData(MarketViewModel viewModel) {
        super.setData(viewModel);

        setDataChart(viewModel.getPeriods());

        icon.setImageResource(getIcon(viewModel.getAsset()));
        tvAsset.setText(viewModel.getAsset());
        tvQuote.setText(viewModel.getQuote());
        tvPrice.setText(viewModel.getPrice().format2DecimalHalfUp());
        tvPercent.setText(viewModel.getPercentFormat());
        tvPercent.setTextColor(getColorPercent(viewModel.getPercent()));
    }

    private int getIcon(String asset) {
        Context context = TraderApplication.getInstance();
        return context.getResources().getIdentifier(asset.toLowerCase(), "drawable", context.getPackageName());
    }

    private int getColorPercent(BigDecimalWrapper percent) {
        return percent.gt(BigDecimalWrapper.ZERO) ? Color.parseColor("#00bfbf") : Color.RED;
    }

    private void initChart() {
        chart.setDrawGridBackground(false);
        chart.setMinOffset(8);
        chart.setTouchEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);
        chart.animateX(1000);

        chart.setBackgroundColor(Color.WHITE);

        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);

        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawZeroLine(true);
        chart.getAxisLeft().setZeroLineColor(Color.GRAY);

        chart.getAxisRight().setEnabled(false);
    }

    private void setDataChart(List<Period> periods) {
        if (!CommonUtils.isListEmpty(periods)) {
            List<Entry> entries = new ArrayList<>();

            Period first = periods.get(0);

            for (int x = 0; x < periods.size(); x++) {
                Period current = periods.get(x);

                float value = current.getHighPrice().minus(first.getHighPrice()).floatValue();
                Entry entry = new Entry(x, value);
                entries.add(entry);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            LineDataSet lineDataSet = createLineDataSet(entries);
            dataSets.add(lineDataSet);
            LineData data = new LineData(dataSets);
            chart.setData(data);
            chart.invalidate();
        } else {
            chart.clear();
        }
    }

    LineDataSet createLineDataSet(List<Entry> values) {
        LineDataSet lineDataSet = new LineDataSet(new ArrayList<>(values), "");

        lineDataSet.enableDashedLine(0, 0, 0);
        lineDataSet.setDrawIcons(false);
        lineDataSet.setColor(Color.parseColor("#1cffff"));
        lineDataSet.setLineWidth(1);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
//        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return 0;
            }
        });

        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.item_market_color_chart);
            lineDataSet.setFillDrawable(drawable);
        } else {
            lineDataSet.setFillColor(Color.parseColor("#1cffff"));
        }

        return lineDataSet;
    }
}
