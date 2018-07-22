package com.huynh.xinh.data.repositories.market.cloud;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceDto {
    private BigDecimal last;
    private BigDecimal high;
    private BigDecimal low;
    private ChangeDto change;
}