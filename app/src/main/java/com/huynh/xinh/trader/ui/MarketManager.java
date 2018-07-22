package com.huynh.xinh.trader.ui;

import com.annimon.stream.Stream;
import com.huynh.xinh.domain.models.Market;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Setter;

public class MarketManager {
    private static MarketManager instance;

    @Setter
    private HashMap<String, List<Market>> marketHashMap;

    private MarketManager() {
        marketHashMap = new HashMap<>();
    }

    public static MarketManager getInstance() {
        if (instance == null) {
            instance = new MarketManager();
        }
        return instance;
    }

    public void pushAll(HashMap<String, List<Market>> marketHashMap) {
        this.marketHashMap.putAll(marketHashMap);
    }

    public void push(String exchangeSymbol, List<Market> markets) {
        marketHashMap.put(exchangeSymbol, markets);
    }

    public List<Market> getMarkets(String exchangeSymbol) {
        return marketHashMap.get(exchangeSymbol);
    }

    public List<String> getAllMarkets() {
        return Stream.of(marketHashMap)
                .map(Map.Entry::getKey).toList();
    }
}
