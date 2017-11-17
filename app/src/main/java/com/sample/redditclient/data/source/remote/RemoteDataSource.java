package com.sample.redditclient.data.source.remote;

import com.sample.redditclient.data.Entry;
import com.sample.redditclient.data.source.DataSource;
import com.sample.redditclient.data.source.remote.service.reddit.RedditServiceProvider;

import java.util.UUID;

import io.reactivex.Single;

import static com.sample.redditclient.data.source.remote.service.reddit.RedditServiceProvider.INSTALLED_CLIENT_GRANT_TYPE;

/**
 * Created by osarapul on 16.11.2017.
 */

public class RemoteDataSource implements DataSource {
    private static final RemoteDataSource instance = new RemoteDataSource();

    private static final int ENTRY_LIMIT = 50;

    private final RedditServiceProvider.Service service;

    private RemoteDataSource() {
        service = RedditServiceProvider.getService();
    }

    public static RemoteDataSource getInstance() {
        return instance;
    }

    @Override
    public Single<Entry[]> getTopRedditEntries() {
        return service.authorize(INSTALLED_CLIENT_GRANT_TYPE, UUID.randomUUID().toString())
                .flatMap(accessToken -> service.getTop("Bearer " + accessToken.accessToken, ENTRY_LIMIT));
    }
}
