package com.huynh.xinh.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coin {
    private long id;
    private String symbol;
    private String route;
    private String name;
    private boolean fiat;
}
