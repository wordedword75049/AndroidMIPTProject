package com.db.dprice.kisapp.resview;

import android.content.Context;
import android.text.TextUtils;

import com.db.dprice.kisapp.database.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteRepository {

    private static final Map<Long, Note> PERSON_LIST = new HashMap<>();

    public static void initialize(final Context context, List<Person> notes) {
        for (Person each : notes) {
            PERSON_LIST.put(each.getId(), new Note(each.getId(), each.getName(), each.getPath(), each.getDate()));
        }
    }

    public static List<Note> getPersonList() {
        return new ArrayList<>(PERSON_LIST.values());
    }

    public static Note getPersonById(final long id) {
        return PERSON_LIST.get(id);
    }
}
