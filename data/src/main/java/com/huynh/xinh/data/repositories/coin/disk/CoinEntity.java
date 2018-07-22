package com.huynh.xinh.data.repositories.coin.disk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "COIN")
public class CoinEntity {
    @PrimaryKey
    private long id;
    private String symbol;
    private String name;
    private boolean fiat;
    private String route;
}
