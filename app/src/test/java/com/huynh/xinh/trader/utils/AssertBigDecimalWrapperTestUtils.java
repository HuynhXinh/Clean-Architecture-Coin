package com.huynh.xinh.trader.utils;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import junit.framework.Assert;

public class AssertBigDecimalWrapperTestUtils extends Assert {
    public static void assertEquals(double expected, double actual) {
        Assert.assertEquals(expected, actual, 0.01);
    }

    public static void assertEquals(BigDecimalWrapper expected, BigDecimalWrapper actual) {
        assertEquals(expected.doubleValue(), actual.doubleValue());
    }

    public static void assertEquals(double expected, BigDecimalWrapper actual) {
        assertEquals(expected, actual.doubleValue());
    }

    public static void assertEquals(BigDecimalWrapper expected, double actual) {
        assertEquals(expected.doubleValue(), actual);
    }
}
