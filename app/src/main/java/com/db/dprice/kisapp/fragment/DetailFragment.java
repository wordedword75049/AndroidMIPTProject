package com.db.dprice.kisapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.db.dprice.kisapp.R;
import com.db.dprice.kisapp.resview.Note;
import com.db.dprice.kisapp.resview.NoteRepository;

public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFragment";

    private static final String NAME_KEY = "NAME_KEY";
    private static final String PATH_KEY = "PATH_KEY";
    private static final String DATE_KEY = "DATE_KEY";

    public static Fragment newInstance(@NonNull final long id) {
        final Fragment fragment = new com.db.dprice.kisapp.fragment.DetailFragment();
        final Note note = NoteRepository.getPersonById(id);
        final Bundle arguments = new Bundle();
        arguments.putString(NAME_KEY, note.getName());
        arguments.putString(PATH_KEY, note.getPath());
        arguments.putString(DATE_KEY, note.getDate());

        fragment.setArguments(arguments);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detatil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView textView = view.findViewById(R.id.detailTextView);

        final String name = getArguments().getString(NAME_KEY);
        final String path = getArguments().getString(PATH_KEY);
        final String date = getArguments().getString(DATE_KEY);
        final String text_to_note= "Name: " + name + '\n' + "Path: " + path + '\n' + "Date: "  + date;
        textView.setText(text_to_note);
    }
}
