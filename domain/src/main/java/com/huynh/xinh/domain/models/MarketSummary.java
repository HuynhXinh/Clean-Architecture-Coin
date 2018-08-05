package com.huynh.xinh.domain.models;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketSummary {
    private String pair;
    private String asset;
    private String quote;
    private BigDecimalWrapper price;
    private BigDecimalWrapper percent;
    private List<Period> periods;
    private boolean isFavorite;
}
