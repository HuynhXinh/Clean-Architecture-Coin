package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.MarketSummary;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MarketViewModelMapper {
    MarketViewModelMapper INSTANCE = Mappers.getMapper(MarketViewModelMapper.class);

    List<MarketViewModel> toMarketViewModels(List<MarketSummary> markets);

    MarketViewModel toMarketViewModel(MarketSummary marketSummary);

    @AfterMapping
    default void additionField(@MappingTarget MarketViewModel marketViewModel) {
        String percentFormat = marketViewModel.getPercent()
                .multiply(BigDecimalWrapper.ONE_HUNDRED)
                .round2DecimalHalfUp()
                .format2DecimalHalfUpSign() + "%";
        marketViewModel.setPercentFormat(percentFormat);
    }
}
