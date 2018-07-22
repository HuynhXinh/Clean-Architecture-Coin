package com.huynh.xinh.domain.models;

import lombok.Getter;

public enum EnumPeriod {
    ONE_MINUTE(60),
    THREE_MINUTE(180),
    FIVE_MINUTE(300),
    FIFTEEN_MINUTE(900),
    THIRTY_MINUTE(1800),
    ONE_HOUR(3600),
    TWO_HOUR(7200),
    FOUR_HOUR(14400),
    SIX_HOUR(21600),
    TWELFTH_HOUR(43200),
    ONE_DAY(86400),
    THREE_DAY(259200),
    ONE_WEEK(604800);

    @Getter
    private final long value;

    EnumPeriod(long value) {
        this.value = value;
    }
}
