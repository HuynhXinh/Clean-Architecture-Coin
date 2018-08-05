package com.huynh.xinh.domain.interactor.detialpair;

import com.huynh.xinh.domain.models.EnumPeriod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPeriodParam {
    private EnumPeriod periodEnum;
    private String marketName;
    private String pair;
    private long after;
    private String periods;
}
