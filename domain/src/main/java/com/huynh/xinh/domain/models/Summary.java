package com.huynh.xinh.domain.models;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Summary {
    private Price price;
    private BigDecimalWrapper volume;
    private BigDecimalWrapper volumeQuote;
}