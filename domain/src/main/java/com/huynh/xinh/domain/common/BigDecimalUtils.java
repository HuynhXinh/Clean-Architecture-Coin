package com.huynh.xinh.domain.common;

import java.math.BigDecimal;

class BigDecimalUtils {

    private BigDecimalUtils() {
    }

    public static BigDecimalUtilsFormat format(BigDecimal bigDecimal) {
        return new BigDecimalUtilsFormat(bigDecimal);
    }

    public static BigDecimalUtilsLogic logic(BigDecimal bigDecimal) {
        return new BigDecimalUtilsLogic(bigDecimal);
    }

    public static BigDecimalUtilsCalculation calculate(BigDecimal value) {
        return new BigDecimalUtilsCalculation(value);
    }

    public static BigDecimalUtilsRound round(BigDecimal value) {
        return new BigDecimalUtilsRound(value);
    }
}