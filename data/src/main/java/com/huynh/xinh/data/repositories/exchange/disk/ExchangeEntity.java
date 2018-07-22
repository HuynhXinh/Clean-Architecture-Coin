package com.huynh.xinh.data.repositories.exchange.disk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "EXCHANGE")
public class ExchangeEntity {
    @PrimaryKey
    private long id;
    private String symbol;
    private String name;
    private String route;
    private boolean active;
}
