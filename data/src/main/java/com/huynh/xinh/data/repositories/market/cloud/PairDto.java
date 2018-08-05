package com.huynh.xinh.data.repositories.market.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PairDto {
    private Base base;
    private Quote quote;

    @Getter
    @Setter
    public static class Base {
        private String symbol;
    }

    @Getter
    @Setter
    public static class Quote {
        private String symbol;
    }
}
