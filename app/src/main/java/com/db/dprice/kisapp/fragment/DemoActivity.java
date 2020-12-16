package com.db.dprice.kisapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.db.dprice.kisapp.R;

public class DemoActivity extends AppCompatActivity {

    public static Intent getIntent(@NonNull final Context context) {
        return new Intent(context, com.db.dprice.kisapp.fragment.DemoActivity.class);
    }

    private int detailFrameLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        if (getResources().getBoolean(R.bool.is_pad)) {
            detailFrameLayoutId = R.id.demoDetailContainer;
        } else {
            detailFrameLayoutId = R.id.demoMainContainer;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.demoMainContainer, ListFragment.newInstance(), ListFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void showDetailFragment(@NonNull final long id) {
        if (getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG) != null) {
            // Если на экране уже есть фрагмент с деталями, то надо его убрать перед показом нового
            getSupportFragmentManager().popBackStack();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(detailFrameLayoutId, DetailFragment.newInstance(id), DetailFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}