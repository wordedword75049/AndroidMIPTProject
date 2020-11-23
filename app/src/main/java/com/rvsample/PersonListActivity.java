package me.shafran.rvsample;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PersonListActivity extends AppCompatActivity implements PersonAdapter.Listener {

    @Override
    public void onPersonClick(final long id) {
        startActivity(PersonDetailActivity.getIntent(this, id));
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

        final PersonAdapter adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setPersonList(PersonRepository.getPersonList());
        adapter.setListener(this);
    }
}
