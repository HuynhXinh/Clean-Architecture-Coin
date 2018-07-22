package com.huynh.xinh.data.repositories.market.cloud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketDto {
    private long id;
    private String exchange;
    private String pair;
    private boolean active;
    private String route;
}
