package com.huynh.xinh.data.common;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import java.math.BigDecimal;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BigDecimalWrapperMapper {

    public BigDecimal toBigDecimal(BigDecimalWrapper bigDecimalWrapper) {
        return bigDecimalWrapper;
    }

    public BigDecimalWrapper toBigDecimalWrapper(BigDecimal bigDecimal) {
        return BigDecimalWrapper.from(bigDecimal);
    }

    public BigDecimalWrapper toBigDecimalWrapper(double value) {
        return BigDecimalWrapper.from(value);
    }

    public String toString(BigDecimalWrapper bigDecimalWrapper) {
        return bigDecimalWrapper.format2DecimalHalfUp();
    }
}
