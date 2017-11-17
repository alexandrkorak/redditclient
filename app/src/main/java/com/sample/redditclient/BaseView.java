package com.sample.redditclient;

import android.support.annotation.NonNull;

/**
 * Created by osarapul on 17.11.2017.
 */

public interface BaseView<P extends BasePresenter> {
    void setPresenter(@NonNull P presenter);

    void setProgressIndicatorActive(boolean active);
}
