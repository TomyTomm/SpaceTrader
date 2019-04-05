package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Constants;
import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;

import java.io.File;

public class ConfigurationActivity extends AppCompatActivity {

    //reference to the view model
    private ConfigurationViewModel viewModel;

    //widgets that will be getting information
    private EditText nameField;
    private EditText pilotPoints;
    private EditText traderPoints;
    private EditText engineerPoints;
    private EditText fighterPoints;
    private CompoundButton easyButton;
    private CompoundButton normalButton;
    private CompoundButton hardButton;
    private CompoundButton insaneButton;
    private GameDifficulty selectedGameDifficulty;
    private TextView pointsRemaining;

    //data for player being configured
//    private Player player;

    //data for game being configured
//    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);


        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.player_name_input);
        pilotPoints = findViewById(R.id.pilot_points_input);
        traderPoints = findViewById(R.id.trader_points_input);
        engineerPoints = findViewById(R.id.engineer_points_input);
        fighterPoints = findViewById(R.id.fighter_points_input);
        easyButton = findViewById(R.id.easy_button);
        normalButton = findViewById(R.id.normal_button);
        hardButton = findViewById(R.id.hard_button);
        insaneButton = findViewById(R.id.insane_button);
        pointsRemaining = findViewById(R.id.points_remaining);

        /* Default value setters */
        selectedGameDifficulty = GameDifficulty.NORMAL;
        normalButton.setChecked(true);

        /* wiring up game difficulty buttons */
        easyButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedGameDifficulty = GameDifficulty.EASY;
                    normalButton.setChecked(false);
                    hardButton.setChecked(false);
                    insaneButton.setChecked(false);
                }
            }
        });

        normalButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedGameDifficulty = GameDifficulty.NORMAL;
                    easyButton.setChecked(false);
                    hardButton.setChecked(false);
                    insaneButton.setChecked(false);
                }
            }
        });

        hardButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedGameDifficulty = GameDifficulty.DIFFICULT;
                    easyButton.setChecked(false);
                    normalButton.setChecked(false);
                    insaneButton.setChecked(false);
                }
            }
        });

        insaneButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedGameDifficulty = GameDifficulty.IMPOSSIBLE;
                    easyButton.setChecked(false);
                    normalButton.setChecked(false);
                    hardButton.setChecked(false);
                }
            }
        });
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    /**
     * Button handler for the okay button
     *
     * @param view the button that was pressed
     */
    public void onOkayPressed(View view) {
        Log.d("Config", "Okay button pressed");
        if (nameField == null) {
            Toast.makeText(this, "Error with game. Not handled",
                    Toast.LENGTH_SHORT).show();
        }
        String name = nameField.getText().toString();

        int pilotPts;
        int traderPts;
        int engineerPts;
        int fighterPts;
        if (!easyButton.isChecked()
                && !normalButton.isChecked()
                && !hardButton.isChecked()
                && !insaneButton.isChecked()) {
            selectedGameDifficulty = GameDifficulty.NORMAL;
        }
        try {
            pilotPts = Integer.parseInt(pilotPoints.getText().toString());
            traderPts = Integer.parseInt(traderPoints.getText().toString());
            engineerPts = Integer.parseInt(engineerPoints.getText().toString());
            fighterPts = Integer.parseInt(fighterPoints.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, "Please enter a value for each point type",
                    Toast.LENGTH_LONG).show();
            return;
        }

        //the data passed in is legal

        viewModel.loadDifficulty(selectedGameDifficulty);
        String result = null;
        try {
            result = viewModel.loadPlayer(name, pilotPts, traderPts, engineerPts, fighterPts);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (result != null) {
            saveConfig();
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * saves game locally
     */
    private void saveConfig() {
        //File to save config to
        File file = new File(this.getFilesDir(), Constants.LOCAL_GAME_SERIALIZATION_FILE);
        if (viewModel.saveGameLocally(file)) {
            Toast.makeText(this, "Saved game locally!", Toast.LENGTH_SHORT).show();
        }
        //Intent sets the next activity to go
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    /**
     * Button handler for cancel - just call back pressed
     *
     * @param view the button pressed
     */
    public void onCancelPressed(View view) {
        onBackPressed();
    }
}


