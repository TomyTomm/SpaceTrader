package com.amath.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    //reference to the view model
    private ConfigurationViewModel viewModel;

    //widgets that will be getting information
    private EditText nameField;
    private EditText pilotPoints;
    private EditText fighterPoints;
    private EditText traderPoints;
    private EditText engineerPoints;
    private Spinner gameDifficultySpinner;

    //data for player being configured
    private Player player;

    //data for game being configured
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        /*
         * Grab the dialog widgets so we can get info for later
         */
        nameField = findViewById(R.id.player_name_input);
        pilotPoints = findViewById(R.id.pilot_points_input);
        fighterPoints = findViewById(R.id.fighter_points_input);
        traderPoints = findViewById(R.id.trader_points_input);
        engineerPoints = findViewById(R.id.engineer_points_input);
        gameDifficultySpinner = findViewById(R.id.game_difficulty_spinner);
        Button okayButton = findViewById(R.id.okay_button);
        Button cancelButton = findViewById(R.id.cancel_button);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<GameDifficulty> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GameDifficulty.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameDifficultySpinner.setAdapter(adapter);
    }

    /**
     * Button handler for the okay button
     *
     * @param view the button that was pressed
     */
    public void onOkayPressed(View view) {
        Log.d("Config", "Okay button pressed");

        //check to see if the data is legal
        if (nameField.getText().toString().length() == 0
                || Integer.parseInt(pilotPoints.getText().toString()) < 0
                || Integer.parseInt(fighterPoints.getText().toString()) < 0
                || Integer.parseInt(traderPoints.getText().toString()) < 0
                || Integer.parseInt(engineerPoints.getText().toString()) < 0
                || Integer.parseInt(pilotPoints.getText().toString()) +
                Integer.parseInt(fighterPoints.getText().toString()) +
                Integer.parseInt(traderPoints.getText().toString()) +
                Integer.parseInt(engineerPoints.getText().toString()) > 16) {
            Log.d("Config", "data passed in is NOT valid");
            throw new IllegalArgumentException("data passed in is NOT valid");
        }

        //the data passed in is legal
        Log.d("Config", "data passed in is valid");
        player = new Player(nameField.getText().toString(), Integer.parseInt(pilotPoints.getText().toString()),
                            Integer.parseInt(fighterPoints.getText().toString()),
                            Integer.parseInt(traderPoints.getText().toString()),
                            Integer.parseInt(engineerPoints.getText().toString()));
        game = new Game(player, (GameDifficulty) gameDifficultySpinner.getSelectedItem());

        viewModel.newGame(game);
        finish();
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