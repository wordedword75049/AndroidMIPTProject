package com.db.dprice.kisapp.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    private static final String LOG_TAG = "PersonRepository";

    private final DatabaseHolder databaseHolder;

    public PersonRepository(@NonNull final DatabaseHolder databaseHolder) {
        this.databaseHolder = databaseHolder;
    }

    public long create(@NonNull final Person person) {
        Log.d(LOG_TAG, "create start");
        try {
            SQLiteDatabase database = databaseHolder.open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(PersonContract.Columns.TEXT, person.getName());
            contentValues.put(PersonContract.Columns.PATH, person.getPath());
            contentValues.put(PersonContract.Columns.DATA, person.getData());

            return database.insert(PersonContract.TABLE_NAME, null, contentValues);
        } finally {
            databaseHolder.close();
            Log.d(LOG_TAG, "create finish");
        }
    }

    public void update(@NonNull final Person person) {
        try {
            SQLiteDatabase database = databaseHolder.open();

            ContentValues contentValues = new ContentValues();
            contentValues.put(PersonContract.Columns.TEXT, person.getName());
            contentValues.put(PersonContract.Columns.PATH, person.getPath());
            contentValues.put(PersonContract.Columns.DATA, person.getData());

            String where = PersonContract.Columns._ID + "= ?";
            String[] whereArgs = new String[] { String.valueOf(person.getId()) };
            database.update(PersonContract.TABLE_NAME, contentValues, where, whereArgs);
        } finally {
            databaseHolder.close();
        }
    }

    public void delete(@NonNull final Person person) {
        try {
            SQLiteDatabase database = databaseHolder.open();

            String where = PersonContract.Columns._ID + "= ?";
            String[] whereArgs = new String[] { String.valueOf(person.getId()) };
            database.delete(PersonContract.TABLE_NAME, where, whereArgs);
        } finally {
            databaseHolder.close();
        }
    }

    public List<Person> loadAll() {
        Log.d(LOG_TAG, "load start");
        List<Person> personList = new ArrayList<>();
        Cursor cursor = null;
        try {
            SQLiteDatabase database = databaseHolder.open();

            cursor = database.query(
                    PersonContract.TABLE_NAME,
                    new String[] {PersonContract.Columns._ID, PersonContract.Columns.TEXT, PersonContract.Columns.PATH, PersonContract.Columns.DATA},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                Person person = new Person();
                person.setId(cursor.getLong(cursor.getColumnIndex(PersonContract.Columns._ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(PersonContract.Columns.TEXT)));
                person.setPath(cursor.getString(cursor.getColumnIndex(PersonContract.Columns.PATH)));
                person.setData(cursor.getString(cursor.getColumnIndex(PersonContract.Columns.DATA)));
                personList.add(person);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            databaseHolder.close();
        }

        Log.d(LOG_TAG, "load finish");
        return personList;
    }
}
