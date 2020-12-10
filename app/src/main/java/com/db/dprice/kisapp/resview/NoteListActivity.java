package com.db.dprice.kisapp.resview;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.db.dprice.kisapp.R;

public class NoteListActivity extends AppCompatActivity implements com.db.dprice.kisapp.resview.NoteAdapter.Listener {

    @Override
    public void onPersonClick(final long id) {
        startActivity(com.db.dprice.kisapp.resview.NoteDetailActivity.getIntent(this, id));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        final RecyclerView recyclerView = findViewById(R.id.personRecyclerView);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 5);

        final com.db.dprice.kisapp.resview.NoteAdapter adapter = new com.db.dprice.kisapp.resview.NoteAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNoteList(com.db.dprice.kisapp.resview.NoteRepository.getPersonList());
        adapter.setListener(this);
    }
}
