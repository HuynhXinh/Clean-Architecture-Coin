package com.huynh.xinh.data.repositories.coin.cloud;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoinDto {
    private long id;
    private String symbol;
    private String name;
    private boolean fiat;
    private String route;
}
