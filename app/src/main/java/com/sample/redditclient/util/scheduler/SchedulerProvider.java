package com.sample.redditclient.util.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by osarapul on 17.11.2017.
 */

public class SchedulerProvider implements BaseSchedulerProvider {
    @NonNull
    private static final SchedulerProvider instance = new SchedulerProvider();

    @NonNull
    public static SchedulerProvider getInstance() {
        return instance;
    }

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
