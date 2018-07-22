package com.huynh.xinh.trader.di.components;

import android.app.Application;

import com.huynh.xinh.trader.TraderApplication;
import com.huynh.xinh.trader.di.modules.ActivityBuilder;
import com.huynh.xinh.trader.di.modules.ApiModule;
import com.huynh.xinh.trader.di.modules.ApplicationModule;
import com.huynh.xinh.trader.di.modules.DatabaseModule;
import com.huynh.xinh.trader.di.modules.JobsModule;
import com.huynh.xinh.trader.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class,
        DatabaseModule.class,
        ApiModule.class,
        RepositoryModule.class,
        JobsModule.class
})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {
    void inject(TraderApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder applicationModule(ApplicationModule applicationModule);

        Builder databaseModule(DatabaseModule databaseModule);

        ApplicationComponent build();
    }
}
