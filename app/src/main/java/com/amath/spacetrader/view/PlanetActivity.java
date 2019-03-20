package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amath.spacetrader.R;
import com.amath.spacetrader.viewmodel.PlanetViewModel;

public class PlanetActivity extends AppCompatActivity {

    PlanetViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solarsystem);

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

    }

    /**
     * Button handler for the fly button
     * Intents are used to move between activities.
     * Please look at the config() under MainActivity.java
     * for an example.
     *
     * @param view the button that was pressed
     */
    public void onFlyPressed(View view) {

//        startActivity(intent);
    }
}
