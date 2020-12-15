package com.db.dprice.kisapp.db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

public class PersonContract {

    public static final String TABLE_NAME = "note_table";

    public interface Columns extends BaseColumns {
        String TEXT = " text ";
        String PATH = " path ";
        String DATA = " date ";
    }

    private PersonContract() {
        // Утилитный класс
    }

    public static void createTable(@NonNull final SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME
                        + " ( "
                        + Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Columns.TEXT + " TEXT NOT NULL"
                        + Columns.PATH + " TEXT NOT NULL"
                        + Columns.DATA + " TEXT NOT NULL"
                        + " );"
        );
    }
}
