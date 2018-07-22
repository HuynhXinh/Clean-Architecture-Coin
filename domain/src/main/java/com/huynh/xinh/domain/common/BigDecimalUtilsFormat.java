package com.huynh.xinh.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.huynh.xinh.domain.common.BigDecimalWrapperConstants.TWO_DECIMAL_SCALE;


class BigDecimalUtilsFormat {
    private final BigDecimal amount;

    public BigDecimalUtilsFormat(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public String format2DecimalUp() {
        return this.amount.setScale(TWO_DECIMAL_SCALE, RoundingMode.UP).toPlainString();
    }

    public String format2DecimalHalfUp() {
        return this.amount.setScale(TWO_DECIMAL_SCALE, RoundingMode.HALF_UP).toPlainString();
    }

    public String format2DecimalHalfUpSign() {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance();
        formatter.setPositivePrefix("+");
        formatter.setNegativePrefix("-");

        return formatter.format(this.amount.setScale(TWO_DECIMAL_SCALE, RoundingMode.HALF_UP));
    }
}