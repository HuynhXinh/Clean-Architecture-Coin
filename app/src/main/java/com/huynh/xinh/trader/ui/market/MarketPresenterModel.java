package com.huynh.xinh.trader.ui.market;

import com.huynh.xinh.domain.models.Exchange;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarketPresenterModel {
    private Exchange exchange;
    private int page;
}
