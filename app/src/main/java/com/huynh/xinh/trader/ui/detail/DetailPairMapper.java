package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;
import com.huynh.xinh.trader.ui.detail.model.DetailPairPresenterModel;
import com.huynh.xinh.trader.ui.detail.model.DetailPairViewModel;
import com.huynh.xinh.trader.ui.detail.model.ItemTabPeriodTimeViewModel;
import com.huynh.xinh.trader.ui.detail.model.PriceAtPeriodTimeViewModel;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static com.huynh.xinh.domain.common.BigDecimalWrapper.ONE_HUNDRED;

@Mapper
public interface DetailPairMapper {
    DetailPairMapper INSTANCE = Mappers.getMapper(DetailPairMapper.class);

    DetailPairViewModel toDetailPairViewModel(Summary summary);

    List<ItemTabPeriodTimeViewModel> toItemTabPeriodTimeViewModels(List<EnumPeriod> periodTimeEnums);

    default ItemTabPeriodTimeViewModel toItemTabPeriodTimeViewModel(EnumPeriod enumPeriod) {
        return ItemTabPeriodTimeViewModel.builder()
                .enumPeriod(enumPeriod)
                .build();
    }

    void toDetailPairPresenterModel(DetailPairFragmentParam detailPairFragmentParam, @MappingTarget DetailPairPresenterModel detailPairPresenterModel);

    default PriceAtPeriodTimeViewModel toPriceAtPeriodTimeViewModel(Period period) {
        String percentFormat =
                period.getClosePrice().minus(period.getOpenPrice())
                        .div(period.getOpenPrice())
                        .multiply(ONE_HUNDRED)
                        .format2DecimalHalfUpSign() + "%";

        return PriceAtPeriodTimeViewModel.builder()
                .date(period.getCloseTime())
                .open(period.getOpenPrice().format2DecimalHalfUp())
                .height(period.getHighPrice().format2DecimalHalfUp())
                .low(period.getLowPrice().format2DecimalHalfUp())
                .close(period.getClosePrice().format2DecimalHalfUp())
                .percentChange(percentFormat)
                .build();
    }
}