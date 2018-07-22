package com.huynh.xinh.domain.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Ohlc {
    private List<Period> oneMinute;
    private List<Period> threeMinute;
    private List<Period> fiveMinute;
    private List<Period> fifteenMinute;
    private List<Period> thirtyMinute;
    private List<Period> oneHour;
    private List<Period> twoHour;
    private List<Period> fourHour;
    private List<Period> sixHour;
    private List<Period> twelfth;
    private List<Period> oneDay;
    private List<Period> threeDay;
    private List<Period> oneWeek;
}
