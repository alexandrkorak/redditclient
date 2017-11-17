package com.sample.redditclient.entry;

import android.support.annotation.NonNull;

import com.sample.redditclient.BasePresenter;
import com.sample.redditclient.data.source.DataSource;
import com.sample.redditclient.util.scheduler.BaseSchedulerProvider;

/**
 * Created by osarapul on 17.11.2017.
 */

public class EntryPresenter extends BasePresenter<EntryView> {
    public EntryPresenter(@NonNull DataSource repository, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(repository, schedulerProvider);
    }

    public void click() {
        view.setActionBarShowing(!view.isActionBarShowing());
    }
}
