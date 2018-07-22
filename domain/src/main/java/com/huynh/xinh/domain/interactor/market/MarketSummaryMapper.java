package com.huynh.xinh.domain.interactor.market;

import com.huynh.xinh.domain.models.MarketDetail;
import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Summary;

import lombok.Builder;

@Builder
public class MarketSummaryMapper {
    private MarketDetail marketDetail;
    private Summary summary;
    private Ohlc ohlc;

    public MarketSummary getMarketSummary() {
        MarketSummary marketSummary = new MarketSummary();
        marketSummary.setPair(marketDetail.getPair());
        marketSummary.setPrice(summary.getPrice().getLast());
        marketSummary.setPercent(summary.getPrice().getChange().getPercentage());
        marketSummary.setPeriods(ohlc.getOneHour());
        return marketSummary;
    }
}
