package com.huynh.xinh.domain.models;

public enum EnumPeriod {
    _1_M("1M", 60), // 60
    _3_M("3M", 3 * 60), // 180
    _5_M("5M", 5 * 60), // 300
    _15_M("15M", 15 * 60), // 900
    _30_M("30M", 30 * 60), // 1800
    _1_H("1H", 60 * 60), // 3600
    _2_H("2H", 2 * 60 * 60), // 7200
    _4_H("4H", 4 * 60 * 60), // 14400
    _6_H("6H", 6 * 60 * 60), // 21600
    _12_H("12H", 12 * 60 * 60), // 43200
    _1_D("1D", 24 * 60 * 60), // 86400
    _3_D("3D", 3 * 24 * 60 * 60), // 259200
    _1_W("1W", 7 * 24 * 60 * 60); // 604800

    public String key;
    public long value; // Time units seconds

    EnumPeriod(String key, long value) {
        this.key = key;
        this.value = value;
    }
}
