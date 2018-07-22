package com.huynh.xinh.data.repositories.market.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailExchangeDto {
    private long id;
    private String symbol;
    private String name;
    private String active;
    private RouteDto routes;
}
