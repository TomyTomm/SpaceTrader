package com.amath.spacetrader.view;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.TechLevel;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.MarketViewModel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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

    Map<Good, TextView> amountView = new HashMap<>();

    TextView credits;
    TextView planetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        credits = findViewById(R.id.player_credits_amount);
        updateCredits();

        planetName = findViewById(R.id.market_header_planet);
        planetName.setText(viewModel.getPlanetName());

        PlanetInventory = viewModel.loadMarket();
        Good[] goods = Good.values();
        TechLevel techLevel = viewModel.getTechLevel();

        TableLayout table = findViewById(R.id.market_table);

        int i = 0;
        for (Good good: goods) {
            View row = getRow();
            prices.put(good, good.calculatePrice(techLevel));
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
            amountView.put(good, amount);

            //Set tags for buy and sell buttons

            Button buyButton = row.findViewById(R.id.buy);
            Button sellButton = row.findViewById(R.id.sell);

            buyButton.setTag(good);
            sellButton.setTag(good);
//            row.getLayoutParams().height = 500;
            row.setMinimumHeight(120);

//            break;
        }

//        TableRow template = findViewById(R.id.good_name);
//        for (int i = 0; i < tradeTable.size(); i++) {
//            newRows[i] = new TableRow(this);
//            newRows[i].setId(i+1);
//            newRows[i].setBackgroundColor(Color.CYAN);
//            newRows[i].setLayoutParams(new TableLayout.LayoutParams(
//                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//
//            goodLabels[i] = new TextView(this);
//            goodLabels[i].setId(i+111);
//            goodLabels[i].setText(goods[i].getType());
//            goodLabels[i].setTextColor(Color.BLUE);
//            newRows[i].addView(goodLabels[i]);
//
//            goodOwned[i] = new TextView(this);
//            goodOwned[i].setId(i+1111);
//            goodLabels[i].setTextColor(Color.BLUE);
//            goodOwned[i].setText(goods[i].getQuantity());
//            newRows[i].addView(goodOwned[i]);
//
//            goodPrice[i] = new TextView(this);
//            goodOwned[i].setId(i+11111);
//            goodLabels[i].setTextColor(Color.BLUE);
//            goodPrice[i].setText(tradeTable.get(goods[i]));
//            newRows[i].addView(goodPrice[i]);
//
//            buyAmount[i] = new EditText(this);
//            buyAmount[i].setId(i*17+1111);
//            buyAmount[i].setHint(0);
//            buyAmount[i].setInputType(InputType.TYPE_CLASS_NUMBER);
//            buyAmount[i].setBackgroundColor(Color.WHITE);
//            newRows[i].addView(buyAmount[i]);
//
//            sellAmount[i] = new EditText(this);
//            sellAmount[i].setId(i*13+11);
//            sellAmount[i].setHint(0);
//            sellAmount[i].setInputType(InputType.TYPE_CLASS_NUMBER);
//            sellAmount[i].setBackgroundColor(Color.WHITE);
//            newRows[i].addView(sellAmount[i]);
//
//            marketTable.addView(newRows[i], new TableLayout.LayoutParams(
//                    TableLayout.LayoutParams.MATCH_PARENT,
//                    TableLayout.LayoutParams.WRAP_CONTENT));
//        }
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
                updateCredits();
                amountView.get(good).setText(String.format("%d", PlanetInventory.get(good)));

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
                updateCredits();
                amountView.get(good).setText(String.format("%d", PlanetInventory.get(good)));

                File file = new File(this.getFilesDir(), "game.txt");
                viewModel.saveGameLocally(file);
            }
        } catch (MarketViewModel.IllegalTradeException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Should use an Intent() and return to UniverseView
     *
     * @param view
     */
    public void onBackPressed(View view) {
        onBackPressed();
    }

    private void updateCredits() {
        credits.setText("$" + viewModel.getPlayerCredits());

    }



    public View getRow() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.good_row, null);
        return view;
    }

}