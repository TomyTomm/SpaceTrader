package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;

public class MarketActivity extends AppCompatActivity {

    /*
    IMPORTANT!!!!

    THINGS WE NEED TO FIGURE OUT BEFORE THIS CLASS CAN BE EDITED.
    - We need to be able to see ship Inventory and also the planet's inventory
    - Need to be able to select how much you buy or sell
    -
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
    }

    /**
     * Processes when the player wants to buy a good
     * Should call correlated method in MarketViewModel
     *
     * @param view
     */
    public void onBuyPressed(View view) { }

    /**
     * Processes when the player wants to sell a good
     * Should call correlated method in MarketViewModel
     *
     * @param view
     */
    public void onSellPressed(View view) { }

    /**
     * Should use an Intent() and return to UniverseView
     *
     * @param view
     */
    public void onBackPressed(View view) {
        Intent intent = new Intent(this, UniverseActivity.class);
        startActivity(intent);
    }

}