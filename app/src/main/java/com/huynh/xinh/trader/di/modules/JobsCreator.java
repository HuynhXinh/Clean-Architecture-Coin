package com.huynh.xinh.trader.di.modules;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class JobsCreator implements JobCreator {
    @Inject
    Map<String, Provider<Job>> jobs;

    @Inject
    JobsCreator() {
    }

    @Override
    public Job create(@NonNull String tag) {
        Provider<Job> jobProvider = jobs.get(tag);
        return jobProvider != null ? jobProvider.get() : null;
    }
}
