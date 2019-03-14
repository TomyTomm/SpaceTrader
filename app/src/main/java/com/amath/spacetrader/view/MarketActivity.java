package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.TechLevel;
import com.amath.spacetrader.viewmodel.MarketViewModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MarketActivity extends AppCompatActivity {

    /*
    IMPORTANT!!!!

    THINGS WE NEED TO FIGURE OUT BEFORE THIS CLASS CAN BE EDITED.
    - We need to be able to see ship Inventory and also the planet's inventory
    - Need to be able to select how much you buy or sell
    -
     */

    Map<Good, Integer> PlanetInventory;
    Map<Good, Integer> prices = new HashMap<>();
    MarketViewModel viewModel;

    Map<Good, TextView> planetInventoryView = new HashMap<>();
    Map<Good, TextView> playerInventoryView = new HashMap<>();

    Map<Good, Button> buyButtonViews = new HashMap<>();
    Map<Good, Button> sellButtonViews = new HashMap<>();

    TextView credits;
    TextView planetName;

    TextView capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        credits = findViewById(R.id.player_credits_amount);
        credits.setText("$" + viewModel.getPlayerCredits());

        capacity = findViewById(R.id.capacity);
        capacity.setText(String.format("%d/%d", viewModel.getInventorySize(), viewModel.getCapacity()));

        planetName = findViewById(R.id.market_header_planet);
        planetName.setText(viewModel.getPlanetName());

        PlanetInventory = viewModel.loadPlanetInventory();
        prices = viewModel.loadMarket();


        Good[] goods = Good.values();
        TechLevel techLevel = viewModel.getTechLevel();

        TableLayout table = findViewById(R.id.market_table);

        int i = 0;
        for (Good good: goods) {
            View row = getRow();
            table.addView(row);
            TextView name = row.findViewById(R.id.good_name);
//            Log.d("testtesttest", tv.getText().toString());
            name.setId(name.getId() + i++);
            name.setTag(good);
            name.setText(good.toString());

            //Set text for price
            TextView price = row.findViewById(R.id.price);
            price.setText(String.format("$%d", prices.get(good)));

            //Set text for amount
            TextView amount = row.findViewById(R.id.amount);
            amount.setText(String.format("%d", PlanetInventory.get(good)));
            planetInventoryView.put(good, amount);

            // Set text for player inventory
            TextView playerInventory = row.findViewById(R.id.inventory);
            playerInventory.setText(String.format("%d", viewModel.getGoodAmount(good)));
            playerInventoryView.put(good, playerInventory);

            //Set tags for buy and sell buttons

            Button buyButton = row.findViewById(R.id.buy);
            Button sellButton = row.findViewById(R.id.sell);

            if (PlanetInventory.get(good) == 0 || viewModel.getPlayerCredits() < prices.get(good)) {
                buyButton.setBackgroundColor(0);
            }
            if (viewModel.getGoodAmount(good) == 0) {
                sellButton.setBackgroundColor(0);
            }

            buyButton.setTag(good);
            sellButton.setTag(good);

            buyButtonViews.put(good, buyButton);
            sellButtonViews.put(good, sellButton);
            row.setMinimumHeight(120);

        }
    }

    /**
     * Processes when the player wants to buy a good
     * Should call correlated method in MarketViewModel
     *
     * @param view
     */
    public void onBuyPressed(View view) {
        Good good = (Good) view.getTag();
        try {
            if (viewModel.verifyBuy(good, 1, prices.get(good), PlanetInventory)) {
                Toast.makeText(this, String.format("Successfully bought %d %s", 1, good.toString()), Toast.LENGTH_LONG).show();
                update(good);
                File file = new File(this.getFilesDir(), "game.txt");
                viewModel.saveGameLocally(file);
            }
        } catch (MarketViewModel.IllegalTradeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Processes when the player wants to sell a good
     * Should call correlated method in MarketViewModel
     *
     * Will use toasts for now, however, will want to use
     * DialogFragments later
     *
     * @param view
     */
    public void onSellPressed(View view) {
        Good good = (Good) view.getTag();
        try {
            if (viewModel.verifySell(good, 1, prices.get(good), PlanetInventory)) {
                Toast.makeText(this, String.format("Successfully sold %d %s", 1, good.toString()), Toast.LENGTH_LONG).show();
                update(good);
            }
        } catch (MarketViewModel.IllegalTradeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     * @param view
     */
    public void onBackPressed(View view) {
        onBackPressed();
    }



    private void update(Good good) {
        credits.setText("$" + viewModel.getPlayerCredits());
        planetInventoryView.get(good).setText(String.format("%d", PlanetInventory.get(good)));
        playerInventoryView.get(good).setText(String.format("%d", viewModel.getGoodAmount(good)));

        for (Good good1: Good.values()) {
            Button buyButton = buyButtonViews.get(good1);
            Button sellButton = sellButtonViews.get(good1);

            if (viewModel.getPlayerCredits() < prices.get(good1)) {
                buyButton.setBackgroundColor(0);
            } else if (PlanetInventory.get(good1) == 0) {
                buyButton.setBackgroundColor(0);
            } else if (viewModel.getInventorySize() == viewModel.getCapacity()) {
                buyButton.setBackgroundColor(0);
            } else {
                Log.d("button color", buyButton.getTag().toString());
                buyButton.setBackgroundColor(getColor(R.color.colorPrimary));
            }

            if (viewModel.getGoodAmount(good1) == 0) {
                sellButton.setBackgroundColor(0);
            } else {
                Log.d("button color", "Not here");
                sellButton.setBackgroundColor(getColor(R.color.colorPrimary));
            }
        }

        capacity.setText(String.format("%d/%d", viewModel.getInventorySize(), viewModel.getCapacity()));

        File file = new File(this.getFilesDir(), "game.txt");
        viewModel.saveGameLocally(file);
    }

    public View getRow() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.good_row, null);
        return view;
    }

}