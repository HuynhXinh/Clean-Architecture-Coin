package com.huynh.xinh.trader;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.huynh.xinh.trader.di.components.DaggerApplicationComponent;
import com.huynh.xinh.trader.di.modules.ApplicationModule;
import com.huynh.xinh.trader.di.modules.DatabaseModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by XinhHuynh on 2/24/2018.
 */

public class TraderApplication extends DaggerApplication {

    private static TraderApplication instance;

    public static TraderApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder()
                .application(this)
                .applicationModule(new ApplicationModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();
    }
}
