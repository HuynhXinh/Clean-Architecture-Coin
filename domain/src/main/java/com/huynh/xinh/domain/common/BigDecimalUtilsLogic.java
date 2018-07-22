package com.huynh.xinh.domain.common;

import java.math.BigDecimal;

class BigDecimalUtilsLogic {

    private final BigDecimal amount;

    public BigDecimalUtilsLogic(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    /**
     * Equals (insensitive to scale).
     */
    public boolean eq(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) == 0;
    }

    /**
     * Greater than.
     */
    public boolean gt(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) > 0;
    }

    /**
     * Greater than or equal to.
     */
    public boolean gteq(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) >= 0;
    }

    /**
     * Less than.
     */
    public boolean lt(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) < 0;
    }

    /**
     * Less than or equal to.
     */
    public boolean lteq(BigDecimal bigDecimal) {
        return this.amount.compareTo(bigDecimal) <= 0;
    }
}