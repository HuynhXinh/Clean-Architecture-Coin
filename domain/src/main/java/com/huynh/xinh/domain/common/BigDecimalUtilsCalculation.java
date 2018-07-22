package com.huynh.xinh.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

class BigDecimalUtilsCalculation {

    private final BigDecimal amount;

    public BigDecimalUtilsCalculation(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BigDecimal minus(BigDecimal value) {
        return amount.subtract(value);
    }

    public BigDecimal plus(double value) {
        BigDecimal plus = new BigDecimal(Double.toString(value));
        return amount.add(plus);
    }

    public BigDecimal plus(BigDecimal value) {
        return amount.add(value);
    }

    public BigDecimal multiply(double value) {

        BigDecimal multiply = new BigDecimal(Double.toString(value));
        return amount.multiply(multiply);
    }

    public BigDecimal multiply(BigDecimal multiply) {
        return amount.multiply(multiply);
    }

    public BigDecimal div(BigDecimal divisor, int scale, RoundingMode roundingMode) {
        return amount.divide(divisor, scale, roundingMode);
    }

    public BigDecimal max(BigDecimal value) {
        return amount.max(value);
    }

    public BigDecimal min(BigDecimal value) {
        return amount.min(value);
    }
}