package com.huynh.xinh.trader.ui.detail.model;

import com.huynh.xinh.domain.models.EnumPeriod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTabPeriodTimeViewModel {
    private EnumPeriod enumPeriod;
    private String name;
    private long value;
}
