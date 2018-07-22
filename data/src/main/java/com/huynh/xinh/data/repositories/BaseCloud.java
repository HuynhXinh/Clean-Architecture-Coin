package com.huynh.xinh.data.repositories;

import retrofit2.Retrofit;

public class BaseCloud {
    protected final Retrofit retrofit;

    public BaseCloud(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
