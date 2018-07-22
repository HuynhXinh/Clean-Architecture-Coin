package com.huynh.xinh.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Exchange {
    private long id;
    private String symbol;
    private String name;
    private String route;
    private boolean active;
}
