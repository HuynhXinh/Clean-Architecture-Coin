package com.huynh.xinh.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Market {
    private long id;
    private String exchange;
    private String pair;
    private String asset;
    private String quote;
    private boolean active;
    private String route;
}
