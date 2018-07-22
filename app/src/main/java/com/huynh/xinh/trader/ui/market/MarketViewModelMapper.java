package com.huynh.xinh.trader.ui.market;

import android.content.Context;
import android.graphics.Color;

import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.trader.TraderApplication;
import com.huynh.xinh.trader.utils.AssetAndQuoteUtils;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MarketViewModelMapper {
    MarketViewModelMapper INSTANCE = Mappers.getMapper(MarketViewModelMapper.class);

    List<MarketViewModel> toMarketViewModels(List<MarketSummary> markets);

    @Mappings({
            @Mapping(target = "asset", expression = "java(getAsset(marketSummary))"),
            @Mapping(target = "quote", expression = "java(getQuote(marketSummary))")
    })
    MarketViewModel toMarketViewModel(MarketSummary marketSummary);

    @AfterMapping
    default void additionField(@MappingTarget MarketViewModel marketViewModel) {
        String percentFormat = marketViewModel.getPercent()
                .multiply(BigDecimalWrapper.ONE_HUNDRED)
                .round2DecimalHalfUp()
                .format2DecimalHalfUpSign() + "%";
        marketViewModel.setPercentFormat(percentFormat);
        marketViewModel.setColorPercent(getColorPercent(marketViewModel.getPercent()));
        marketViewModel.setIcon(getIcon(marketViewModel.getAsset()));
    }

    default String getAsset(MarketSummary marketSummary) {
        return AssetAndQuoteUtils.getAsset(marketSummary.getPair()).toUpperCase();
    }

    default String getQuote(MarketSummary marketSummary) {
        return AssetAndQuoteUtils.getQuote(marketSummary.getPair()).toUpperCase();
    }

    default int getColorPercent(BigDecimalWrapper percent) {
        return percent.gt(BigDecimalWrapper.ZERO) ? Color.parseColor("#00bfbf") : Color.RED;
    }

    default int getIcon(String asset) {
        Context context = TraderApplication.getInstance();
        return context.getResources().getIdentifier(asset.toLowerCase(), "drawable", context.getPackageName());
    }
}
