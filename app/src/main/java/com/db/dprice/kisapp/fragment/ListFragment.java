package com.db.dprice.kisapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.dprice.kisapp.MainActivity;
import com.db.dprice.kisapp.resview.Note;
import com.db.dprice.kisapp.resview.NoteAdapter;
import com.db.dprice.kisapp.resview.NoteDetailActivity;
import com.db.dprice.kisapp.resview.NoteRepository;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.db.dprice.kisapp.R;

public class ListFragment extends Fragment implements NoteAdapter.Listener {

    public static final String TAG = "ListFragment";

    public static Fragment newInstance() {
        return new com.db.dprice.kisapp.fragment.ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.personRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        recyclerView.setHasFixedSize(true);
        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 5);

        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setNoteList(NoteRepository.getPersonList());
        adapter.setListener(this);
    }

    @Override
    public void onPersonClick(long id) {
        ((MainActivity) getActivity()).showDetailFragment(id);
    }
}
