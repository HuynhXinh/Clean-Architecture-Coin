package com.huynh.xinh.domain.models;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Price {
    private BigDecimalWrapper last;
    private BigDecimalWrapper high;
    private BigDecimalWrapper low;
    private Change change;
}