package com.db.dprice.kisapp.resview;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteRepository {

    private static final Map<Long, Note> PERSON_LIST = new HashMap<>();

    public static void initialize(final Context context) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("names.txt")))) {
            String name = reader.readLine();
            long id = 0;
            while (!TextUtils.isEmpty(name)) {
                PERSON_LIST.put(id, new Note(id, name, path, date));
                ++id;
                name = reader.readLine();
            }
        } catch (IOException e) {
            // Ничего не делать
        }
    }

    public static List<Note> getPersonList() {
        return new ArrayList<>(PERSON_LIST.values());
    }

    public static Note getPersonById(final long id) {
        return PERSON_LIST.get(id);
    }
}
