package com.sample.redditclient.entries;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.redditclient.BaseViewFragment;
import com.sample.redditclient.R;
import com.sample.redditclient.data.Entry;
import com.sample.redditclient.entry.EntryActivity;
import com.sample.redditclient.util.ActivityUtil;

import butterknife.BindView;

/**
 * Created by osarapul on 17.11.2017.
 */

public class EntriesViewFragment extends BaseViewFragment<EntriesPresenter, BaseViewFragment.ActivityListener> implements EntriesView {
    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    public static EntriesViewFragment newInstance() {
        return new EntriesViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entires, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listener.setProgressIndicatorActive(true);
        recyclerView.setPadding(0, ActivityUtil.getStatusBarHeight(getActivity()) + ActivityUtil.getActionBarHeight(getActivity()), 0, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        presenter.start();
    }

    @Override
    public void onDestroyView() {
        presenter.stop();
        super.onDestroyView();
    }

    @Override
    public void setEntries(@NonNull Entry[] entries) {
        recyclerView.setAdapter(
                new EntriesAdapter(entries, entry ->
                        EntryActivity.startActivityFrom(getActivity(), entry)));
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isFinishing() {
        return getActivity().isFinishing();
    }

    @Override
    public void showInternetConnectionFailed() {
        Snackbar.make(getView(), R.string.connection_failed, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, view -> presenter.retry())
                .show();
    }
}
