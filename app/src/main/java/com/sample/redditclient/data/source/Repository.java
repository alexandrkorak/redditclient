package com.sample.redditclient.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sample.redditclient.data.Entry;
import com.sample.redditclient.data.source.remote.RemoteDataSource;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by osarapul on 16.11.2017.
 */

public class Repository implements DataSource {
    @NonNull
    private static final Repository instance = new Repository(RemoteDataSource.getInstance());

    @NonNull
    private final DataSource remoteDataSource;
    @Nullable
    private Entry[] entryCache;

    public Repository(@NonNull DataSource remoteDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }

    @NonNull
    public static Repository getInstance() {
        return instance;
    }

    @Override
    public Single<Entry[]> getTopRedditEntries() {
        if (entryCache != null) {
            return Single.just(entryCache);
        }

        return remoteDataSource
                .getTopRedditEntries()
                .doOnSuccess(entries -> entryCache = entries);
    }
}
