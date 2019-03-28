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
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.UniverseViewModel;

public class UniverseActivity extends AppCompatActivity {
    //reference to the view model
    private UniverseViewModel viewModel;

    //tableView
    private TableLayout solarSystemTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        viewModel = ViewModelProviders.of(this).get(UniverseViewModel.class);

        // Set the current solar system
        TextView currentSolarSystem = findViewById(R.id.current_solarsystem);
        currentSolarSystem.setText(viewModel.getCurrentSolarSystemName());

        this.solarSystemTable = findViewById(R.id.table);
        printNames();
        populateSolarSystemTable();
    }

    public void populateSolarSystemTable() {
        Log.i("universeActivity", "Populating Solar System Table");
        int rowIndex = 0;
        for (SolarSystem system: viewModel.getUniverse().getSolarSystems()) {

            // Get row
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.universe_row, null);

            solarSystemTable.addView(row);

            // Populate row subviews
            TextView SystemName = row.findViewById(R.id.system_name);
            SystemName.setId(SystemName.getId() + rowIndex);
            SystemName.setText(system.getName());

            Log.d("universeActivity", "Name:" + system.getName());

            Button travelButton = row.findViewById(R.id.travel);
            travelButton.setId(travelButton.getId() + rowIndex);
            travelButton.setTag(system);

            rowIndex++;
        }
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

    // Handlers

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
     * Button handler for travel to solar system
     * @param view button that was pressed
     */
    public void onTravelButtonPressed(View view) {
        Log.i("universeActivity", "Travelling to solar system");
        Intent intent = new Intent(this, SolarSystemActivity.class);
        intent.putExtra("system", (SolarSystem)view.getTag());
        startActivityForResult(intent, 1);
    }

    public void onBackPressed(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
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
