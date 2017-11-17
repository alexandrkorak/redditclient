package com.sample.redditclient.entry;

import com.sample.redditclient.BaseView;

/**
 * Created by osarapul on 17.11.2017.
 */

public interface EntryView extends BaseView<EntryPresenter> {
    boolean isActionBarShowing();

    void setActionBarShowing(boolean showing);
}
