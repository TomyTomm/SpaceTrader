package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TableRow;

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