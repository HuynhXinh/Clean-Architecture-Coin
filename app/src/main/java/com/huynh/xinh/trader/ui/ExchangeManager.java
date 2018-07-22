package com.huynh.xinh.trader.ui;

import com.huynh.xinh.domain.models.Exchange;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ExchangeManager {
    private static ExchangeManager instance;

    @Getter
    private List<Exchange> exchanges;

    public static ExchangeManager getInstance() {
        if (instance == null) {
            instance = new ExchangeManager();
        }
        return instance;
    }

    private ExchangeManager() {
        exchanges = new ArrayList<>();
    }

    public void set(List<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    public void clear() {
        exchanges.clear();
    }
}
