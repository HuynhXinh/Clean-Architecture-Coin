package com.huynh.xinh.data.repositories.market.disk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "MARKET")
public class MarketEntity {
    @PrimaryKey
    private long id;
    private String exchange;
    private String pair;
    private String asset;
    private String quote;
    private boolean active;
    private String route;
}
