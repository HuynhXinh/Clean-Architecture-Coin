package com.huynh.xinh.data.repositories.exchange;

import com.huynh.xinh.data.repositories.exchange.cloud.ExchangeDto;
import com.huynh.xinh.data.repositories.exchange.disk.ExchangeEntity;
import com.huynh.xinh.domain.models.Exchange;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ExchangeMapper {
    ExchangeMapper INSTANCE = Mappers.getMapper(ExchangeMapper.class);

    List<ExchangeEntity> toExchangeEntities(List<ExchangeDto> exchangeDtos);

    ExchangeEntity toExchangeEntity(ExchangeDto exchangeDto);

    List<Exchange> toExchanges(List<ExchangeEntity> exchangeEntities);

    Exchange toExchange(ExchangeEntity exchangeEntity);
}
