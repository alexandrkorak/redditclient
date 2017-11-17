package com.sample.redditclient.entries;

import android.support.annotation.NonNull;

import com.sample.redditclient.BaseActivity;
import com.sample.redditclient.data.source.Repository;
import com.sample.redditclient.util.scheduler.SchedulerProvider;

public class EntriesActivity extends BaseActivity<EntriesViewFragment, EntriesPresenter> {
    @NonNull
    @Override
    protected EntriesViewFragment createViewFragment() {
        return EntriesViewFragment.newInstance();
    }

    @NonNull
    @Override
    protected EntriesPresenter createPresenter() {
        return new EntriesPresenter(Repository.getInstance(), SchedulerProvider.getInstance());
    }
}
