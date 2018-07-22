package com.huynh.xinh.domain.interactor.market;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMarketParam {
    private String exchangeName;
    private int page;
    private long after;
    private String periods;
}
