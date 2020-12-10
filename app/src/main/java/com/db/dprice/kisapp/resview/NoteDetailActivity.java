package com.db.dprice.kisapp.resview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.db.dprice.kisapp.R;

public class NoteDetailActivity extends AppCompatActivity {

    
    private static final String ID_KEY = "ID_KEY";

    public static Intent getIntent(final Context context, final long id) {
        final Intent intent = new Intent(context, com.db.dprice.kisapp.resview.NoteDetailActivity.class);
        intent.putExtra(ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        final long id = getIntent().getLongExtra(ID_KEY, -1);
        final Note note = NoteRepository.getPersonById(id);

        final TextView textView = findViewById(R.id.personNameTextView);
        textView.setText(note.getName());
    }
}
