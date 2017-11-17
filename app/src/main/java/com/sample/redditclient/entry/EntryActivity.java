package com.sample.redditclient.entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sample.redditclient.BaseActivity;
import com.sample.redditclient.R;
import com.sample.redditclient.data.Entry;
import com.sample.redditclient.data.source.Repository;
import com.sample.redditclient.util.scheduler.SchedulerProvider;

import butterknife.BindView;

/**
 * Created by osarapul on 16.11.2017.
 */

public class EntryActivity extends BaseActivity<EntryViewFragment, EntryPresenter> {
    private static final String ENTRY_KEY = "ENTRY";

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    public static void startActivityFrom(@NonNull Activity activity, @NonNull Entry entry) {
        Intent intent = new Intent(activity, EntryActivity.class);
        intent.putExtra(ENTRY_KEY, entry);
        activity.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar((Entry) getIntent().getSerializableExtra(ENTRY_KEY));
    }

    @NonNull
    @Override
    protected EntryViewFragment createViewFragment() {
        return EntryViewFragment.newInstance((Entry) getIntent().getSerializableExtra(ENTRY_KEY));
    }

    @NonNull
    @Override
    protected EntryPresenter createPresenter() {
        return new EntryPresenter(Repository.getInstance(), SchedulerProvider.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar(@NonNull Entry entry) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(String.valueOf(entry.title));
        }
    }
}
