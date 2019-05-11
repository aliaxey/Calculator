package com.example.drcreeper.awesomecalculator.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.drcreeper.awesomecalculator.R;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        ActionBar bar = getSupportActionBar();
        bar.setTitle(R.string.history);
    }
}
