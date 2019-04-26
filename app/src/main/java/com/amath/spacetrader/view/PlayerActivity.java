package com.amath.spacetrader.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Weapon;
import com.amath.spacetrader.viewmodel.PlayerViewModel;

import org.w3c.dom.Text;

public class PlayerActivity extends AppCompatActivity {

    private PlayerViewModel viewModel;
    private CompoundButton cannon;
    private CompoundButton missile;
    private CompoundButton knife;
    private TextView weaponDesc;
    private TextView weaponCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        cannon = findViewById(R.id.cannon_button);
        missile = findViewById(R.id.missile_button);
        knife = findViewById(R.id.knife_button);
        weaponDesc = findViewById(R.id.weapon_desc_header);
        weaponCost = findViewById(R.id.weapon_cost);
        setInformationViews();
        /* wiring up game difficulty buttons */
        cannon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (viewModel.inventoryStatusCheckAndChange(Weapon.CANNON)) {
                    if (isChecked) {
                        missile.setChecked(false);
                        knife.setChecked(false);
                        viewModel.inventoryStatusCheckAndChange(Weapon.CANNON);
                        weaponDesc.setText("A standard but reliable choice.");
                        weaponCost.setText("(1 space cost)");
                    } else if (!isChecked && !knife.isChecked() && !missile.isChecked()) {
                        viewModel.inventoryStatusCheckAndChange(Weapon.NONE);
                        weaponDesc.setText("Nothing selected. Pacifist.");
                        weaponCost.setText("");
                    }
                }
            }
        });

        missile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (viewModel.inventoryStatusCheckAndChange(Weapon.PROTON_TORPEDOES)) {
                    if (isChecked) {
                        cannon.setChecked(false);
                        knife.setChecked(false);
                        viewModel.inventoryStatusCheckAndChange(Weapon.PROTON_TORPEDOES);
                        weaponDesc.setText("Requires more space, but presents more of a threat.");
                        weaponCost.setText("(2 space cost)");
                    } else if (!isChecked && !cannon.isChecked() && !knife.isChecked()) {
                        viewModel.inventoryStatusCheckAndChange(Weapon.NONE);
                        weaponDesc.setText("Nothing selected. Pacifist.");
                        weaponCost.setText("");
                    }
                }
            }
        });

        knife.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (viewModel.inventoryStatusCheckAndChange(Weapon.KNIFE)) {
                    if (isChecked) {
                        missile.setChecked(false);
                        cannon.setChecked(false);
                        viewModel.inventoryStatusCheckAndChange(Weapon.KNIFE);
                        weaponDesc.setText("Six inches of folded damascus steel and a wicked edge.");
                        weaponCost.setText("(0 space cost)");
                    } else if (!isChecked && !cannon.isChecked() && !missile.isChecked()) {
                        viewModel.inventoryStatusCheckAndChange(Weapon.NONE);
                        weaponDesc.setText("Nothing selected. Pacifist.");
                        weaponCost.setText("");
                    }
                }
            }
        });
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
