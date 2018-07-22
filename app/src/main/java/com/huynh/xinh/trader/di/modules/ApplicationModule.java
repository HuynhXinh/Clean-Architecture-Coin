package com.huynh.xinh.trader.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.huynh.xinh.data.executor.JobExecutor;
import com.huynh.xinh.domain.executor.PostExecutionThread;
import com.huynh.xinh.domain.executor.ThreadExecutor;
import com.huynh.xinh.trader.TraderApplication;
import com.huynh.xinh.trader.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private static final String PRE_NAME = "pref_trader";

    private TraderApplication application;

    public ApplicationModule(TraderApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return application.getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }
}
