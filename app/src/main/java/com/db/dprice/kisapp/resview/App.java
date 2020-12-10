package com.db.dprice.kisapp.resview;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        com.db.dprice.kisapp.resview.NoteRepository.initialize(this);
    }
}
