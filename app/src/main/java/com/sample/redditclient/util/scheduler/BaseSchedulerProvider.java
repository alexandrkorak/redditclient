package com.sample.redditclient.util.scheduler;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by osarapul on 17.11.2017.
 */

public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
