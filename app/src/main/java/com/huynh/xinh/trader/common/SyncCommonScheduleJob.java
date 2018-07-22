package com.huynh.xinh.trader.common;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Set;

import javax.inject.Inject;

public class SyncCommonScheduleJob extends Job {
    public static final String TAG = "SyncCommonScheduleJob";

    @Inject
    SyncCommonScheduleJob() {
    }

    public static JobRequest schedulePeriodicJob() {
        Set<JobRequest> jobRequests = JobManager.instance().getAllJobRequestsForTag(TAG);
        if (!jobRequests.isEmpty()) {
            return jobRequests.iterator().next();
        }

        return new JobRequest.Builder(TAG)
                .setPeriodic(JobRequest.MIN_INTERVAL, JobRequest.MIN_FLEX)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .setRequirementsEnforced(true)
                .build();
    }

    @NonNull
    @Override
    protected Result onRunJob(@NonNull Params params) {
        return Result.SUCCESS;
    }
}
