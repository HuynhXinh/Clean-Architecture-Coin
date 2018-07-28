package com.huynh.xinh.domain.interactor.detialpair;

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
public class GetDetailPairParam {
    private String marketName;
    private String pair;
}
