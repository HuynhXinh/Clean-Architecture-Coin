package com.huynh.xinh.trader.ui.detail.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceAtPeriodTimeViewModel {
    private long date;
    private String open;
    private String height;
    private String low;
    private String close;
    private String percentChange;
}
