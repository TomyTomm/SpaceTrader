package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.UniverseViewModel;

public class UniverseActivity extends AppCompatActivity {
    //reference to the view model
    private UniverseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        printNames();
    }

    public void printNames() {
        Universe universe = viewModel.getUniverse();
        Log.d("universe", String.valueOf(universe.getSolarSystems().size()));
        for (SolarSystem system: universe.getSolarSystems()) {
            for (Planet planet: system.getPlanets()) {
                Log.d("universe", planet.toString());
            }
        }
    }
}
