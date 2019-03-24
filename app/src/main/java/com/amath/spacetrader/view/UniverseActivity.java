package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.UniverseInteractor;
import com.amath.spacetrader.viewmodel.UniverseViewModel;

import java.util.HashMap;
import java.util.Map;

public class UniverseActivity extends AppCompatActivity {
    //reference to the view model
    private UniverseViewModel viewModel;
    private TableLayout systemsTable;
    private UniverseInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);
        printNames();
        interactor = Model.getInstance().getUniverseInteractor();
        systemsTable = findViewById(R.id.system_table);

        Map<SolarSystem, Double> systemDistances = new HashMap<>();

        Button marketButton = findViewById(R.id.market_button);

        Universe universe = viewModel.getUniverse();
        Log.d("universe", String.valueOf(universe.getSolarSystems().size()));
        int i = 0;
        for (SolarSystem system: universe.getSolarSystems()) {

            View row = getRow();
            systemsTable.addView(row);
            TextView name = row.findViewById(R.id.system_name);
            name.setId(name.getId() + i++);
            name.setTag(system);
            name.setText(system.getName());

            //Set text for distance
            TextView distance = row.findViewById(R.id.system_distance);
            distance.setId(distance.getId() + i++);
            distance.setTag(system);
            SolarSystem currentSolarSystem;
            currentSolarSystem = Model.getInstance().getGame().getCurrentPlanet().getSolarSystem();
            double distanceCalc = interactor.calculateDistanceBetweenSolarSystems(currentSolarSystem, system);
            String text = String.format("%7f units", distanceCalc);
            distance.setText(text);

            Button button = findViewById(R.id.fly_button);
            button.setTag(system);
        }
    }

    public void onFlyPress(View view) {
        // Flight
        SolarSystem system = (SolarSystem) view.getTag();
        Intent intent = new Intent(this, SolarSystemActivity.class);
        intent.putExtra("System", system);
        startActivity(intent);
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

    /**
     * Button handler for planet info
     * @param view the planet that was pressed
     */
    public void onPlanetPressed(View view) {
        SolarSystem system = (SolarSystem) view.getTag();

        Intent intent = new Intent(this, SolarSystemActivity.class);
        intent.putExtra("System", system);
        startActivity(intent);
    }
    public View getRow() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.system_row, null);
        return view;
    }
}
