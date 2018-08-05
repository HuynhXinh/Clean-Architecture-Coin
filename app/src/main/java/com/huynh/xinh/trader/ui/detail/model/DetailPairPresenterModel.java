package com.huynh.xinh.trader.ui.detail.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailPairPresenterModel {
    private String marketName;
    private String marketSymbol;
    private String pair;
    private String asset;
    private String quote;
}
