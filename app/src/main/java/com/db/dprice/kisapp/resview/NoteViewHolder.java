package com.db.dprice.kisapp.resview;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.db.dprice.kisapp.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private TextView personNameTextView;
    private long id;

    public NoteViewHolder(
            final View itemView,
            final com.db.dprice.kisapp.resview.NoteAdapter.Listener listener
    ) {
        super(itemView);
        personNameTextView = itemView.findViewById(R.id.personNameTextView);
        personNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (listener != null) {
                    listener.onPersonClick(id);
                }
            }
        });
    }

    public void bind(final com.db.dprice.kisapp.resview.Note person) {
        personNameTextView.setText(person.getName());
        id = person.getId();
    }
}
