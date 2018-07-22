package com.huynh.xinh.data.repositories.coin;

import com.huynh.xinh.data.repositories.coin.cloud.CoinDto;
import com.huynh.xinh.data.repositories.coin.disk.CoinEntity;
import com.huynh.xinh.domain.models.Coin;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CoinMapper {
    CoinMapper INSTANCE = Mappers.getMapper(CoinMapper.class);

    List<CoinEntity> toCoinEntities(List<CoinDto> coinDtos);

    CoinEntity toCoinEntity(CoinDto coinDto);

    List<Coin> toCoins(List<CoinEntity> coinEntities);

    Coin toCoin(CoinEntity coinEntity);

}
