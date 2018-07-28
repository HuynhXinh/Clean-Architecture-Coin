package com.huynh.xinh.trader.ui.detail;

import com.huynh.xinh.domain.models.Summary;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DetailPairViewModelMapper {
    DetailPairViewModelMapper INSTANCE = Mappers.getMapper(DetailPairViewModelMapper.class);

    DetailPairViewModel toDetailPairViewModel(Summary summary);

    @AfterMapping
    default void additionField(@MappingTarget DetailPairViewModel detailPairViewModel) {

    }
}
