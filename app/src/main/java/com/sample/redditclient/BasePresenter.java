package com.sample.redditclient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sample.redditclient.data.source.DataSource;
import com.sample.redditclient.util.scheduler.BaseSchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by osarapul on 17.11.2017.
 */

public class BasePresenter<V extends BaseView> {
    @NonNull
    protected final DataSource repository;

    @NonNull
    protected final BaseSchedulerProvider schedulerProvider;

    @Nullable
    protected V view;

    public BasePresenter(@NonNull DataSource repository, @NonNull BaseSchedulerProvider schedulerProvider) {
        this.repository = checkNotNull(repository);
        this.schedulerProvider = checkNotNull(schedulerProvider);
    }

    public void setView(@NonNull V view) {
        this.view = checkNotNull(view);
    }
}
