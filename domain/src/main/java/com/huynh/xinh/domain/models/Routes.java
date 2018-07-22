package com.huynh.xinh.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Routes {
    private String summary;
    private String ohlc;
    private String price;
    private String orderbook;
    private String trades;
}