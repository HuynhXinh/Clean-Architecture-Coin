package com.huynh.xinh.data.repositories.market;

import com.huynh.xinh.data.common.BigDecimalWrapperMapper;
import com.huynh.xinh.data.repositories.market.cloud.ChangeDto;
import com.huynh.xinh.data.repositories.market.cloud.MarketDetailDto;
import com.huynh.xinh.data.repositories.market.cloud.MarketDto;
import com.huynh.xinh.data.repositories.market.cloud.OhlcDto;
import com.huynh.xinh.data.repositories.market.cloud.PriceDto;
import com.huynh.xinh.data.repositories.market.cloud.RouteDtos;
import com.huynh.xinh.data.repositories.market.cloud.SummaryDto;
import com.huynh.xinh.data.repositories.market.disk.MarketEntity;
import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.Change;
import com.huynh.xinh.domain.models.Market;
import com.huynh.xinh.domain.models.MarketDetail;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Period;
import com.huynh.xinh.domain.models.Price;
import com.huynh.xinh.domain.models.Routes;
import com.huynh.xinh.domain.models.Summary;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;

@Mapper(uses = BigDecimalWrapperMapper.class)
public abstract class MarketMapper {
    public static MarketMapper INSTANCE = Mappers.getMapper(MarketMapper.class);

    abstract List<MarketEntity> toMarketEntities(List<MarketDto> markeDtos);

    abstract MarketEntity toMarketEntity(MarketDto marketDto);

    abstract List<Market> toMarkets(List<MarketEntity> marketEntities);

    abstract Market toMarket(MarketEntity marketEntity);

    abstract MarketDetail toMarketDetail(MarketDetailDto marketDetailDto);

    abstract Routes toRoutes(RouteDtos routeDtos);

    abstract Summary toSummary(SummaryDto summaryDto);

    abstract Price toPrice(PriceDto priceDto);

    abstract Change toChange(ChangeDto changeDto);

    abstract Ohlc toOhlc(OhlcDto ohlcDto);

    abstract List<Period> toPeriods(List<List<BigDecimal>> periodDtos);

    Period toPeriod(List<BigDecimal> periodDto) {
        Period period = new Period();
        period.setCloseTime(periodDto.get(0).longValue());
        period.setOpenPrice(BigDecimalWrapper.from(periodDto.get(1)));
        period.setHighPrice(BigDecimalWrapper.from(periodDto.get(2)));
        period.setLowPrice(BigDecimalWrapper.from(periodDto.get(3)));
        period.setClosePrice(BigDecimalWrapper.from(periodDto.get(4)));
        period.setVolume(BigDecimalWrapper.from(periodDto.get(5)));

        BigDecimalWrapper change = period.getClosePrice().minus(period.getOpenPrice());
        period.setChange(change);
        return period;
    }
}
