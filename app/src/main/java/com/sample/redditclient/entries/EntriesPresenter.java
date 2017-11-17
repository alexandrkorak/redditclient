package com.sample.redditclient.entries;

import android.support.annotation.NonNull;

import com.sample.redditclient.BasePresenter;
import com.sample.redditclient.data.source.DataSource;
import com.sample.redditclient.util.scheduler.BaseSchedulerProvider;

import io.reactivex.disposables.Disposable;

/**
 * Created by osarapul on 17.11.2017.
 */

public class EntriesPresenter extends BasePresenter<EntriesView> {
    private Disposable disposable;

    public EntriesPresenter(@NonNull DataSource repository, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(repository, schedulerProvider);
    }

    private void processError(@NonNull Throwable throwable) {

    }

    private void run() {
        disposable = repository.getTopRedditEntries()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe((entries, throwable) -> {
                    disposable = null;
                    if (view != null) {
                        if (throwable == null) view.setEntries(entries);
                        else processError(throwable);
                        view.setProgressIndicatorActive(false);
                    }
                });
    }

    public void start() {
        if (disposable == null) run();
    }

    public void stop() {
        if (disposable != null && view.isFinishing()) {
            disposable.dispose();
            disposable = null;
        }
    }

    public void retry() {
        view.setProgressIndicatorActive(true);
        run();
    }
}
