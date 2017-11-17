package com.sample.redditclient.entries;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.redditclient.R;
import com.sample.redditclient.data.Entry;
import com.squareup.picasso.Picasso;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by osarapul on 16.11.2017.
 */

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.ViewHolder> {
    @NonNull
    private final Entry[] entries;

    @NonNull
    private final Listener listener;

    public EntriesAdapter(@NonNull Entry[] entries, @NonNull Listener listener) {
        this.entries = checkNotNull(entries);
        this.listener = checkNotNull(listener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reddit_entry, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setEntry(entries[position]);
    }

    @Override
    public int getItemCount() {
        return entries.length;
    }

    interface Listener {
        void entryClicked(@NonNull Entry entry);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView textView;
        private final ImageView imageView;
        private Entry entry;

        public ViewHolder(@NonNull View view, @NonNull Listener listener) {
            super(view);
            textView = view.findViewById(R.id.text);
            imageView = view.findViewById(R.id.image);
            imageView.setOnClickListener(imageView -> listener.entryClicked(entry));
        }

        public void setEntry(@NonNull Entry entry) {
            this.entry = checkNotNull(entry);
            textView.setText(entry.title);
            if (entry.thumbnail != null) {
                imageView.setVisibility(View.VISIBLE);
                Picasso.with(itemView.getContext()).load(String.valueOf(entry.thumbnail)).into(imageView);
            } else imageView.setVisibility(View.GONE);
        }
    }
}
