package com.huynh.xinh.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.huynh.xinh.domain.common.BigDecimalWrapperConstants.TWO_DECIMAL_SCALE;
import static com.huynh.xinh.domain.common.BigDecimalWrapperConstants.ZERO_DECIMAL_SCALE;

class BigDecimalUtilsRound {
    private final BigDecimal amount;

    public BigDecimalUtilsRound(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BigDecimal round2DecimalHalfUp() {
        return this.amount.setScale(TWO_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal round() {
        return this.amount.setScale(ZERO_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal ceil() {
        return this.amount.setScale(ZERO_DECIMAL_SCALE, RoundingMode.CEILING);
    }
}