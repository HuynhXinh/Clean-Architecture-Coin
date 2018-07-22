package com.huynh.xinh.data.repositories.exchange.cloud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDto {
    private long id;
    private String symbol;
    private String name;
    private String route;
    private boolean active;
}
