package com.huynh.xinh.trader.ui.detail.model;

import com.huynh.xinh.domain.common.BigDecimalWrapper;
import com.huynh.xinh.domain.models.Change;
import com.huynh.xinh.domain.models.Price;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.neworld.spanner.Spanner;

import static com.huynh.xinh.domain.common.BigDecimalWrapper.ONE_HUNDRED;

@NoArgsConstructor
public class DetailPairViewModel {
    @Setter
    private Price price;

    public String getLastPrice() {
        return price.getLast().format2DecimalHalfUp();
    }

    public String getChange() {
        Change change = price.getChange();
        return new Spanner(change.getAbsolute().format2DecimalHalfUpSign())
                .append("(")
                .append(change.getPercentage().multiply(ONE_HUNDRED).format2DecimalHalfUp())
                .append("%")
                .append(")")
                .toString();
    }

    public boolean isPriceIncrease() {
        return price.getChange().getAbsolute().gt(BigDecimalWrapper.ZERO);
    }

    public String getHigh() {
        return price.getHigh().format2DecimalHalfUp();
    }

    public String getLow() {
        return price.getLow().format2DecimalHalfUp();
    }
}
