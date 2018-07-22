package com.huynh.xinh.domain.models;

import com.huynh.xinh.domain.common.BigDecimalWrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Change {
    private BigDecimalWrapper absolute;
    private BigDecimalWrapper percentage;
}