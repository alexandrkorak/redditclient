package com.sample.redditclient.entries;

import android.support.annotation.NonNull;

import com.sample.redditclient.BaseView;
import com.sample.redditclient.data.Entry;

/**
 * Created by osarapul on 17.11.2017.
 */

public interface EntriesView extends BaseView<EntriesPresenter> {
    void setEntries(@NonNull Entry[] entries);

    boolean isFinishing();

    void showInternetConnectionFailed();
}
