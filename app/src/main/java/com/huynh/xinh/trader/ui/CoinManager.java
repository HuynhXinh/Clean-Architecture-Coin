package com.huynh.xinh.trader.ui;

import com.huynh.xinh.domain.models.Coin;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class CoinManager {
    private static CoinManager instance;

    @Getter
    private List<Coin> coins;

    public static CoinManager getInstance() {
        if (instance == null) {
            instance = new CoinManager();
        }
        return instance;
    }

    private CoinManager() {
        coins = new ArrayList<>();
    }

    public void set(List<Coin> coins) {
        this.coins = coins;
    }

    public void clear() {
        coins.clear();
    }
}
