package com.amath.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    //reference to the view model
    private ConfigurationViewModel viewModel;

    //widgets that will be getting information
    private Spinner gameDifficultySpinner;
    private EditText nameField;

    //data for player being configured
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
