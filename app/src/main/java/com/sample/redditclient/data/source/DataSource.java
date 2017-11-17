package com.sample.redditclient.data.source;

import com.sample.redditclient.data.Entry;

import io.reactivex.Single;

/**
 * Created by osarapul on 16.11.2017.
 */

public interface DataSource {
    Single<Entry[]> getTopRedditEntries();
}
