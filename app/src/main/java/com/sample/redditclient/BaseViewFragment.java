package com.sample.redditclient;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by osarapul on 17.11.2017.
 */

public abstract class BaseViewFragment<P extends BasePresenter, AL extends BaseViewFragment.ActivityListener> extends Fragment
        implements BaseView<P> {
    @Nullable
    protected P presenter;
    protected Unbinder unbinder;

    protected AL listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (AL) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) listener = (AL) activity;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
        this.presenter = checkNotNull(presenter);
        presenter.setView(this);
    }

    @Override
    public void setProgressIndicatorActive(boolean active) {
        listener.setProgressIndicatorActive(active);
    }

    public interface ActivityListener {
        void setProgressIndicatorActive(boolean active);

        boolean isActionBarShowing();

        void setActionBarShowing(boolean showing);
    }
}
