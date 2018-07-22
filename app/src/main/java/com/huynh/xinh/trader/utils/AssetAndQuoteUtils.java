package com.huynh.xinh.trader.utils;

import com.annimon.stream.Stream;
import com.huynh.xinh.domain.models.Coin;
import com.huynh.xinh.trader.ui.CoinManager;

public class AssetAndQuoteUtils {
    public static String getAsset(String pair) {
        if (!CommonUtils.isEmpty(pair)) {
            return Stream.of(CoinManager.getInstance().getCoins())
                    .map(Coin::getSymbol)
                    .filter(pair::startsWith)
                    .findSingle()
                    .orElse("");
        }

        return "";
    }
    public static String getQuote(String pair) {
        if (!CommonUtils.isEmpty(pair)) {
            return Stream.of(CoinManager.getInstance().getCoins())
                    .map(Coin::getSymbol)
                    .filter(pair::endsWith)
                    .findSingle()
                    .orElse("");
        }

        return "";
    }
}
