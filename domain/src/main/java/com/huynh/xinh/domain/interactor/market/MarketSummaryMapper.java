package com.huynh.xinh.domain.interactor.market;

import com.huynh.xinh.domain.models.MarketSummary;
import com.huynh.xinh.domain.models.Ohlc;
import com.huynh.xinh.domain.models.Summary;

import lombok.Builder;

@Builder
public class MarketSummaryMapper {
    private String pair;
    private String asset;
    private String quote;
    private Summary summary;
    private Ohlc ohlc;

    public MarketSummary getMarketSummary() {
        MarketSummary marketSummary = new MarketSummary();
        marketSummary.setPair(pair);
        marketSummary.setAsset(asset);
        marketSummary.setQuote(quote);
        marketSummary.setPrice(summary.getPrice().getLast());
        marketSummary.setPercent(summary.getPrice().getChange().getPercentage());
        marketSummary.setPeriods(ohlc.getOneHour());
        return marketSummary;
    }
}
