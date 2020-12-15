package com.db.dprice.kisapp.resview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.db.dprice.kisapp.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    public interface Listener {

        void onPersonClick(long id);

    }

    private List<Note> noteList = new ArrayList<>();
    private Listener listener;

    public void setListener(final Listener listener) {
        this.listener = listener;
    }

    public void setNoteList(final List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(
                R.layout.person_list_item,
                parent,
                false
        );
        Log.d("PersonAdapter", "onCreateViewHolder");
        return new NoteViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, final int position) {
        holder.bind(noteList.get(position));
        Log.d("PersonAdapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
