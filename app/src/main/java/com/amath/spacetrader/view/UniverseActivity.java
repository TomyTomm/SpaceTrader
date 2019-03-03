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

public class UniverseActivity extends AppCompatActivity {
    //reference to the view model
    private UniverseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        printNames();

        Button marketButton = findViewById(R.id.market_button);
    }

    public void printNames() {
        Universe universe = viewModel.getUniverse();
        Log.d("universe", String.valueOf(universe.getSolarSystems().size()));
        for (SolarSystem system: universe.getSolarSystems()) {
//            for (Planet planet: system.getPlanets()) {
//                Log.d("universe", planet.toString());
//            }
            Log.d("universe", "\n" + system.toString());
        }
    }

    /**
     * Button handler for the market button
     *
     * @param view the button that was pressed
     */
    public void onMarketPressed(View view) {
        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }
}
