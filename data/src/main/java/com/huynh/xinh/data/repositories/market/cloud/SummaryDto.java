package com.huynh.xinh.data.repositories.market.cloud;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryDto {
    private PriceDto price;
    private BigDecimal volume;
    private BigDecimal volumeQuote;
}