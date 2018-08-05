package com.huynh.xinh.trader.ui.detail.model;

import java.io.Serializable;

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
public class DetailPairFragmentParam implements Serializable {
    private String marketName;
    private String marketSymbol;
    private String pair;
    private String asset;
    private String quote;
}
