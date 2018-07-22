package com.huynh.xinh.trader.di.modules;

import android.content.Context;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.huynh.xinh.trader.common.SyncCommonScheduleJob;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module(includes = ApplicationModule.class)
public class JobsModule {

    @Provides
    @Singleton
    JobManager provideJobManager(Context context, JobsCreator jobCreator) {
        JobManager.create(context).addJobCreator(jobCreator);
        return JobManager.instance();
    }

    @Provides
    @IntoMap
    @StringKey(SyncCommonScheduleJob.TAG)
    Job provideSyncCommonScheduleJob(SyncCommonScheduleJob job) {
        return job;
    }
}