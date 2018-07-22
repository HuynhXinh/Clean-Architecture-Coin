package com.huynh.xinh.data.repositories.market.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketDetailDto {
    private long id;
    private String exchange;
    private String pair;
    private boolean active;
    private RouteDtos routes;
}
