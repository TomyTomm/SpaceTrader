package com.amath.spacetrader.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.viewmodel.PlayerViewModel;

import org.w3c.dom.Text;

public class PlayerActivity extends AppCompatActivity {

    private PlayerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);

        setInformationViews();
    }

    /**
     * Sets TextViews containing player name, current planet,
     * current solar system, and fuel remaining to current values
     * as set by game.
     */
    public void setInformationViews() {
        TextView playerName = findViewById(R.id.player_name);
        playerName.setText(viewModel.getPlayerName());

        TextView currentPlanet = findViewById(R.id.current_planet);
        currentPlanet.setText(viewModel.getCurrentPlanetName());

        TextView currentSolarSystem = findViewById(R.id.current_solarsystem);
        currentSolarSystem.setText(viewModel.getCurrentSolarSystemName());

        TextView fuelRemaining = findViewById(R.id.fuel_remaining);
        fuelRemaining.setText(String.format("%.4f", viewModel.getFuelRemaining()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                setInformationViews();
            }
        }
    }//onActivityResult

    public void onMarketPressed(View view) {
        Log.i("playerActivity", "Going to market");
        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }

    public void onViewUniverse(View view) {
        Log.i("playerActivity", "Going to view the universe");
        Log.d("refactor", "Inside load planets");
        System.out.println("WTFWTFWTF");
        Intent intent = new Intent(this, UniverseActivity.class);
        startActivityForResult(intent, 1);
    }
}
