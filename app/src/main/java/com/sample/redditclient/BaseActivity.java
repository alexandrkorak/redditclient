package com.sample.redditclient;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.sample.redditclient.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by osarapul on 16.11.2017.
 */

public abstract class BaseActivity<VF extends BaseViewFragment, P extends BasePresenter> extends AppCompatActivity
        implements BaseViewFragment.ActivityListener {
    private static final String ACTION_BAR_IS_SHOWING_KEY = "ACTION_BAR_IS_SHOWING_KEY";
    private static final long DURATION = 600L;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;
    private Unbinder unbinder;
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActivityUtil.hideSystemUI(this);

        if (savedInstanceState != null && !savedInstanceState.getBoolean(ACTION_BAR_IS_SHOWING_KEY)) {
            toolbar.setVisibility(View.GONE);
            toolbar.setAlpha(0.f);
        }

        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ActivityUtil.getActionBarHeight(this));
        params.setMargins(0, ActivityUtil.getStatusBarHeight(this), 0, 0);
        toolbar.setLayoutParams(params);

        findOrCreateViewFragment()
                .setPresenter(
                        presenter = findOrCreatePresenter());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(ACTION_BAR_IS_SHOWING_KEY, isActionBarShowing());
        super.onSaveInstanceState(outState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @NonNull
    protected abstract VF createViewFragment();

    @NonNull
    protected abstract P createPresenter();

    @NonNull
    private VF findOrCreateViewFragment() {
        VF viewFragment =
                (VF) getFragmentManager().findFragmentById(R.id.content);
        if (viewFragment == null) {
            viewFragment = createViewFragment();
            ActivityUtil.addFragmentToActivity(
                    getFragmentManager(), viewFragment, R.id.content);
        }
        return viewFragment;
    }

    @NonNull
    private P findOrCreatePresenter() {
        P presenter = (P) getLastCustomNonConfigurationInstance();
        if (presenter == null) presenter = createPresenter();
        return presenter;
    }

    @Override
    public void setProgressIndicatorActive(boolean active) {
        progressBar.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    private void hideActionBar() {
        if (isActionBarShowing()) {
            toolbar.animate().alpha(0.f).setDuration(DURATION)
                    .setInterpolator(new AccelerateInterpolator()).withEndAction(() -> {
                toolbar.setVisibility(View.GONE);
            }).start();
        }
    }

    private void showActionBar() {
        if (!isActionBarShowing()) {
            toolbar.animate().alpha(1.f).setDuration(DURATION)
                    .setInterpolator(new DecelerateInterpolator()).withStartAction(() -> {
                toolbar.setVisibility(View.VISIBLE);
            }).start();
        }
    }

    @Override
    public boolean isActionBarShowing() {
        return toolbar.getVisibility() == View.VISIBLE;
    }

    @Override
    public void setActionBarShowing(boolean showing) {
        if (showing) showActionBar();
        else hideActionBar();
    }
}
