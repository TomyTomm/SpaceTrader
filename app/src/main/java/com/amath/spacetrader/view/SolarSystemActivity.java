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
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.SolarSystemInteractor;
import com.amath.spacetrader.viewmodel.SolarSystemViewModel;

import java.util.HashMap;
import java.util.Map;

public class SolarSystemActivity extends AppCompatActivity {

    private SolarSystemInteractor interactor;
    private TableLayout planetTable;
    private SolarSystemViewModel viewModel;
    private SolarSystem viewedSystem;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extra = getIntent().getExtras();
        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);
        viewedSystem = (SolarSystem) extra.get("System");
        planetTable = findViewById(R.id.planets_table);
        interactor = Model.getInstance().getSolarSystemInteractor();
        Map<Planet, Double> planets;
        Button marketButton = findViewById(R.id.market_button);
        planets = viewModel.loadPlanets(viewedSystem);
        int i = 0;
        for (Planet planet: viewedSystem.getPlanets()) {
            View row = getRow();
            planetTable.addView(row);
            TextView name = row.findViewById(R.id.planet_name);
            name.setId(name.getId() + i++);
            name.setTag(planet);
            name.setText(planet.getName());

            //Set text for distance
            TextView distance = row.findViewById(R.id.planet_distance);
            distance.setId(distance.getId() + i++);
            distance.setTag(planet);
            Planet currentPlanet;
            currentPlanet = Model.getInstance().getGame().getCurrentPlanet();
            String text = String.format("%7f units", planets.get(planet));
            distance.setText(text);

            Button button = findViewById(R.id.fly_button);
            button.setTag(planet);
        }
    }
    public void onPlanetPressed(View view) {
        Planet planet = (Planet) view.getTag();

        Intent intent = new Intent(this, PlanetActivity.class);
        intent.putExtra("Planet", planet);
        startActivity(intent);
    }

    public View getRow() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.planet_row, null);
        return view;
    }
}
