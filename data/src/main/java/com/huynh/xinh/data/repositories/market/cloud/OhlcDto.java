package com.huynh.xinh.data.repositories.market.cloud;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OhlcDto {
    @SerializedName("60")
    private List<List<BigDecimal>> oneMinute;

    @SerializedName("180")
    private List<List<BigDecimal>> threeMinute;

    @SerializedName("300")
    private List<List<BigDecimal>> fiveMinute;

    @SerializedName("900")
    private List<List<BigDecimal>> fifteenMinute;

    @SerializedName("1800")
    private List<List<BigDecimal>> thirtyMinute;

    @SerializedName("3600")
    private List<List<BigDecimal>> oneHour;

    @SerializedName("7200")
    private List<List<BigDecimal>> twoHour;

    @SerializedName("14400")
    private List<List<BigDecimal>> fourHour;

    @SerializedName("21600")
    private List<List<BigDecimal>> sixHour;

    @SerializedName("43200")
    private List<List<BigDecimal>> twelfth;

    @SerializedName("86400")
    private List<List<BigDecimal>> oneDay;

    @SerializedName("259200")
    private List<List<BigDecimal>> threeDay;

    @SerializedName("604800")
    private List<List<BigDecimal>> oneWeek;
}
