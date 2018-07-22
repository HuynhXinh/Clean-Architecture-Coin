package com.huynh.xinh.trader.ui.market;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.Period;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketViewModel {
    private String pair;
    private String asset;
    private String quote;
    private BigDecimalWrapper price;
    private BigDecimalWrapper percent;
    private List<Period> periods;
    private boolean isFavorite;

    private String percentFormat;
    @ColorInt
    private int colorPercent;
    @DrawableRes
    private int icon;
}
