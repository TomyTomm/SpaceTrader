package com.amath.spacetrader.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amath.spacetrader.viewmodel.EventViewModel;

public class EventActivity extends AppCompatActivity {

    private EventViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onBackPressed(View view) {
        onBackPressed();
    }
}
