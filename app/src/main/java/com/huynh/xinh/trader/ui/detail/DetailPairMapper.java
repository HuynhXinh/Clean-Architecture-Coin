package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.models.EnumPeriod;
import com.huynh.xinh.domain.models.Summary;
import com.huynh.xinh.trader.ui.detail.model.DetailPairFragmentParam;
import com.huynh.xinh.trader.ui.detail.model.DetailPairPresenterModel;
import com.huynh.xinh.trader.ui.detail.model.DetailPairViewModel;
import com.huynh.xinh.trader.ui.detail.model.ItemTabPeriodTimeViewModel;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DetailPairMapper {
    DetailPairMapper INSTANCE = Mappers.getMapper(DetailPairMapper.class);

    DetailPairViewModel toDetailPairViewModel(Summary summary);

    List<ItemTabPeriodTimeViewModel> toItemTabPeriodTimeViewModels(List<EnumPeriod> periodTimeEnums);

    default ItemTabPeriodTimeViewModel toItemTabPeriodTimeViewModel(EnumPeriod enumPeriod) {
        return ItemTabPeriodTimeViewModel.builder()
                .enumPeriod(enumPeriod)
                .name(getName(enumPeriod))
                .value(getValue(enumPeriod))
                .build();
    }

    default String getName(EnumPeriod enumPeriod) {
        return enumPeriod.key;
    }

    default long getValue(EnumPeriod enumPeriod) {
        return enumPeriod.value;
    }

    void toDetailPairPresenterModel(DetailPairFragmentParam detailPairFragmentParam, @MappingTarget DetailPairPresenterModel detailPairPresenterModel);
}