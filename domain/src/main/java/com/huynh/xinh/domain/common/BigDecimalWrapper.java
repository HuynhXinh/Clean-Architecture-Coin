package com.huynh.xinh.domain.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import lombok.Getter;

import static com.huynh.xinh.domain.common.BigDecimalWrapperConstants.DEFAULT_ROUNDING_MODE;
import static com.huynh.xinh.domain.common.BigDecimalWrapperConstants.DEFAULT_SCALE;

public class BigDecimalWrapper extends BigDecimal implements Serializable {

    public static final BigDecimalWrapper ZERO = BigDecimalWrapper.from(0);
    public static final BigDecimalWrapper ONE = BigDecimalWrapper.from(1);
    public static final BigDecimalWrapper TEN = BigDecimalWrapper.from(10);
    public static final BigDecimalWrapper ONE_HUNDRED = BigDecimalWrapper.from(100);

    private final BigDecimal bigDecimal;

    private BigDecimalWrapper(String s) {
        super(s);
        this.bigDecimal = new BigDecimal(s).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public static BigDecimalWrapper from(String value) {
        if (value == null || value.isEmpty()) {
            return new BigDecimalWrapper("0");
        }
        return new BigDecimalWrapper(value);
    }

    public static BigDecimalWrapper from(double value) {
        return new BigDecimalWrapper(Double.toString(value));
    }

    public static BigDecimalWrapper from(BigDecimal value) {
        if (value == null) {
            return new BigDecimalWrapper("0");
        }
        return new BigDecimalWrapper(value.toPlainString());
    }

    public BigDecimalWrapper abs() {
        return from(this.bigDecimal.abs());
    }

    /**
     * Format
     */

    public String format2DecimalUp() {
        return BigDecimalUtils.format(this.bigDecimal).format2DecimalUp();
    }

    public String format2DecimalHalfUp() {
        return BigDecimalUtils.format(this.bigDecimal).format2DecimalHalfUp();
    }


    public String format2DecimalHalfUpSign() {
        return BigDecimalUtils.format(this.bigDecimal).format2DecimalHalfUpSign();
    }

    /**
     * Logic
     */

    // Equal
    public boolean eq(double value) {
        return eq(from(value));
    }

    // Equal
    public boolean eq(BigDecimalWrapper value) {
        return BigDecimalUtils.logic(this.bigDecimal).eq(from(value));
    }

    // Greater than
    public boolean gt(double value) {
        return gt(from(value));
    }

    public boolean gt(BigDecimalWrapper value) {
        return BigDecimalUtils.logic(this.bigDecimal).gt(value);
    }

    // Greater than equal
    public boolean gteq(double value) {
        return gteq(from(value));
    }

    // Greater than equal
    public boolean gteq(BigDecimalWrapper value) {
        return BigDecimalUtils.logic(this.bigDecimal).gteq(from(value));
    }

    // Less than
    public boolean lt(double value) {
        return lt(from(value));
    }

    // Less than
    public boolean lt(BigDecimalWrapper value) {
        return BigDecimalUtils.logic(this.bigDecimal).lt(value);
    }

    // Less than equal
    public boolean lteq(double value) {
        return lteq(from(value));
    }

    // Less than equal
    public boolean lteq(BigDecimalWrapper value) {
        return BigDecimalUtils.logic(this.bigDecimal).lteq(from(value));
    }

    /**
     * Calculation
     */

    public BigDecimalWrapper minus(double value) {
        return minus(from(value));
    }

    public BigDecimalWrapper minus(BigDecimalWrapper value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).minus(value);

        return from(calculate);
    }

    public BigDecimalWrapper plus(double value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).plus(value);

        return from(calculate);
    }

    public BigDecimalWrapper plus(BigDecimalWrapper value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).plus(value);

        return from(calculate);
    }

    public BigDecimalWrapper multiply(double value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).multiply(value);

        return from(calculate);
    }

    public BigDecimalWrapper multiply(BigDecimalWrapper value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).multiply(value);

        return from(calculate);
    }

    public BigDecimalWrapper div(double value) {
        return div(from(value));
    }

    public BigDecimalWrapper div(BigDecimalWrapper value) {
        return div(value, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public BigDecimalWrapper div(BigDecimalWrapper value, int scale, RoundingMode roundingMode) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).div(value, scale, roundingMode);

        return from(calculate);
    }

    public BigDecimalWrapper max(BigDecimalWrapper value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).max(value);

        return from(calculate);
    }

    public BigDecimalWrapper min(BigDecimalWrapper value) {
        BigDecimal calculate = BigDecimalUtils.calculate(this.bigDecimal).min(value);

        return from(calculate);
    }

    public BigDecimalWrapper multiply(BigDecimalWrapper value, RoundingMode roundingMode) {
        return BigDecimalWrapper.from(super.multiply(value, new MathContext(0, roundingMode)));
    }

    /**
     * Round
     */

    public BigDecimalWrapper round2DecimalHalfUp() {
        BigDecimal bigDecimal = BigDecimalUtils.round(this.bigDecimal).round2DecimalHalfUp();

        return BigDecimalWrapper.from(bigDecimal);
    }

    public BigDecimalWrapper setScale(int newScale, RoundingMode roundingMode) {
        return BigDecimalWrapper.from(super.setScale(newScale, roundingMode));
    }

    public BigDecimalWrapper round() {
        BigDecimal bigDecimal = BigDecimalUtils.round(this.bigDecimal).round();

        return from(bigDecimal);
    }

    public BigDecimalWrapper ceil() {
        BigDecimal bigDecimal = BigDecimalUtils.round(this.bigDecimal).ceil();

        return from(bigDecimal);
    }
}
