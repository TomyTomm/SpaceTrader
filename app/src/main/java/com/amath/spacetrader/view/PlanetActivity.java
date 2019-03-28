package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.model.PlanetInteractor;
import com.amath.spacetrader.model.SolarSystemInteractor;
import com.amath.spacetrader.viewmodel.PlanetViewModel;

public class PlanetActivity extends AppCompatActivity {

    PlanetViewModel viewModel;

    Planet currentPlanet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentPlanet = (Planet) extras.get("planet");
            Log.e("planetActivity", currentPlanet.toString());
            Double distance = (Double) extras.get("distance");
            Log.e("planetActivity", distance.toString());
            TextView distanceView = findViewById(R.id.distance2);
            String viewData = String.format("%.2f ly", distance);
            distanceView.setText(viewData);
            //The key argument here must match that used in the other activity
        } else {
            Double distance = 0.0;
            TextView distanceView = findViewById(R.id.distance2);
            String viewData = String.format("%.2f ly", distance);
            distanceView.setText(viewData);
            Log.e("planetActivity", "Unable to fetch current planet");
        }

        // Populate activity
        TextView name = findViewById(R.id.planet_name);
        name.setText(currentPlanet.getName());


        TextView status = findViewById(R.id.planet_status);

        try {
            status.setText(currentPlanet.getStatus().toString());

        } catch (NullPointerException e) {
            status.setText("None");
        }

        TextView techLevel = findViewById(R.id.planet_techlevel);
        techLevel.setText(String.format("%d (%s)", currentPlanet.getTechLevel().getLevel(), currentPlanet.getTechLevel().toString()));

        TextView resourceLevel = findViewById(R.id.planet_resourcelevel);
        resourceLevel.setText(String.format("%d (%s)", currentPlanet.getResourceLevel().getLevel(), currentPlanet.getResourceLevel().toString()));
    }

    // Handlers

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
        Log.i("planetActivity", "Fly Button Pressed");
    }

    public void onBackPressed(View view) {
        finish();
    }
}
