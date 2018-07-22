package com.huynh.xinh.trader.utils;

import java.util.List;

/**
 * Created by XinhHuynh on 12/3/2017.
 */

public class CommonUtils {
    public static boolean isListEmpty(List<?> data) {
        return null == data || data.size() == 0;
    }

    public static boolean isNull(Object object) {
        return null == object;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.isEmpty();
    }
}
