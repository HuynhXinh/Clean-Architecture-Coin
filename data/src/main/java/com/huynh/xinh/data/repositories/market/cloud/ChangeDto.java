package com.huynh.xinh.data.repositories.market.cloud;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class ChangeDto {
    private BigDecimal absolute;
    private BigDecimal percentage;
}