package com.huynh.xinh.domain.models;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import lombok.Getter;
import lombok.Setter;

// [ CloseTime, OpenPrice, HighPrice, LowPrice, ClosePrice, Volume ]
@Getter
@Setter
public class Period {
    private long closeTime;
    private BigDecimalWrapper openPrice;
    private BigDecimalWrapper highPrice;
    private BigDecimalWrapper lowPrice;
    private BigDecimalWrapper closePrice;
    private BigDecimalWrapper volume;
    private BigDecimalWrapper change;
}
