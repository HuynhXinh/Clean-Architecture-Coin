package com.huynh.xinh.domain.utils;

public class ValidateStringUtil {
    public static boolean notEmpty(String str) {
        return str != null && str.length() > 0;
    }
}
