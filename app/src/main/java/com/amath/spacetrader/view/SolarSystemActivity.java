package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.UniverseViewModel;
import com.amath.spacetrader.viewmodel.SolarSystemViewModel;

public class SolarSystemActivity extends AppCompatActivity {
    //reference to the view model
    private SolarSystemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);

        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        printNames();

        Button marketButton = findViewById(R.id.market_button);
    }

    public void printNames() {
        SolarSystem solarSystem = viewModel.getSolarSystem();
        Log.d("solarSystem", String.valueOf(solarSystem.getPlanets().size()));
        for (Planet planet: solarSystem.getPlanets()) {
            Log.d("universe", planet.toString());
        }
    }
}
