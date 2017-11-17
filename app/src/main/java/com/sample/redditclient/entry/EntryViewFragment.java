package com.sample.redditclient.entry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sample.redditclient.BaseViewFragment;
import com.sample.redditclient.R;
import com.sample.redditclient.data.Entry;
import com.squareup.picasso.Picasso;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by osarapul on 17.11.2017.
 */

public class EntryViewFragment extends BaseViewFragment<EntryPresenter, BaseViewFragment.ActivityListener>
        implements EntryView {
    private static final String ENTRY_KEY = "ENTRY";

    @BindView(R.id.image)
    protected ImageView imageView;

    public static EntryViewFragment newInstance(@NonNull Entry entry) {
        Bundle args = new Bundle();
        args.putSerializable(ENTRY_KEY, entry);
        EntryViewFragment fragment = new EntryViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        URL url = ((Entry) getArguments().getSerializable(ENTRY_KEY)).thumbnail;
        if (url != null) Picasso.with(getActivity()).load(String.valueOf(url)).into(imageView);

        imageView.setOnClickListener(v -> presenter.click());
    }

    @Override
    public boolean isActionBarShowing() {
        return listener.isActionBarShowing();
    }

    @Override
    public void setActionBarShowing(boolean showing) {
        listener.setActionBarShowing(showing);
    }
}
