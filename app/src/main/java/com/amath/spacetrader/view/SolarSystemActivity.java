package com.amath.spacetrader.view;

import android.app.Activity;
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
import com.amath.spacetrader.viewmodel.SolarSystemViewModel;

import java.util.Map;

public class SolarSystemActivity extends AppCompatActivity {

    private SolarSystemViewModel viewModel;

    private TableLayout planetTable;
    private SolarSystem currentSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solarsystem);
        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentSystem = (SolarSystem) extras.get("system");
            //The key argument here must match that used in the other activity
        } else {
            Log.e("solarSystemActivity", "Unable to fetch current solar system");
        }

        TextView currentSystemName = findViewById(R.id.currentSolarSystem);
        currentSystemName.setText(currentSystem.getName());
        planetTable = findViewById(R.id.table);

        populatePlanetTable();
    }

    private void populatePlanetTable() {
        int rowIndex = 0;
        Map<Planet, Double> planetDistances = viewModel.getPlanetDistances(currentSystem);
        for (Planet planet: currentSystem.getPlanets()) {
            // Get row
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.solarsystem_row, null);

            planetTable.addView(row);

            // Populate row subviews
            TextView SystemName = row.findViewById(R.id.planet_name);
            SystemName.setId(SystemName.getId() + rowIndex);
            SystemName.setText(planet.getName());

            // Populate planet distances
            TextView planetDistance = row.findViewById(R.id.planet_distance);
            planetDistance.setId(planetDistance.getId() + rowIndex);
            planetDistance.setText(String.format("%.2f ly", planetDistances.get(planet)));

            Button travelButton = row.findViewById(R.id.travel);
            travelButton.setId(travelButton.getId() + rowIndex);
            travelButton.setTag(new PlanetAndDistance(planet, planetDistances.get(planet)));

            rowIndex++;
        }
    }

    // handlers

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }//onActivityResult

    /**
     * Button handler to view a planet
     * @param view button that was pressed
     */
    public void onViewButtonPressed(View view) {
        Log.i("solarSystemActivity", "Travelling to planet");
        Intent intent = new Intent(this, PlanetActivity.class);
        PlanetAndDistance planetAndDistance = (PlanetAndDistance) view.getTag();
        intent.putExtra("planet", planetAndDistance.planet);
        intent.putExtra("distance", planetAndDistance.distance);
        startActivityForResult(intent, 1);
    }

    public void onBackPressed(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    private class PlanetAndDistance {
        private Planet planet;
        private Double distance;

        public PlanetAndDistance(Planet planet, Double distance) {
            this.planet = planet;
            this.distance = distance;
        }
    }
}
