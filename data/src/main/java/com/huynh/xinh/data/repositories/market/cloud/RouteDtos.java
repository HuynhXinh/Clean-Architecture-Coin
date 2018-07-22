package com.huynh.xinh.data.repositories.market.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteDtos {
    private String summary;
    private String ohlc;
    private String price;
    private String orderbook;
    private String trades;
}