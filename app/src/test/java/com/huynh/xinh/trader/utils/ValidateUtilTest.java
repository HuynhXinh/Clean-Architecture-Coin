package com.huynh.xinh.trader.utils;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ValidateUtilTest {

    @Test
    public void isListEmpty() {
        List listEmpty = Collections.emptyList();

        assertTrue(CommonUtils.isListEmpty(listEmpty));
    }

    @Test
    public void isNull() {
    }

    @Test
    public void isNonNull() {
    }
}