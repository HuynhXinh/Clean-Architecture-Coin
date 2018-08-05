package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static com.huynh.xinh.domain.common.BigDecimalWrapper.ONE_HUNDRED;

@Mapper
public interface MarketViewModelMapper {
    MarketViewModelMapper INSTANCE = Mappers.getMapper(MarketViewModelMapper.class);

    List<ItemMarketViewModel> toMarketViewModels(List<MarketSummary> markets);

    ItemMarketViewModel toMarketViewModel(MarketSummary marketSummary);

    @AfterMapping
    default void additionField(@MappingTarget ItemMarketViewModel marketViewModel) {
        String percentFormat = marketViewModel.getPercent()
                .multiply(ONE_HUNDRED)
                .format2DecimalHalfUpSign() + "%";
        marketViewModel.setPercentFormat(percentFormat);
    }

    DetailPairFragmentParam toDetailPairParam(ItemMarketViewModel marketViewModel);
}
