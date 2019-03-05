package com.amath.spacetrader.view;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.MarketViewModel;

import java.util.HashMap;

public class MarketActivity extends AppCompatActivity {

    // reference to the MarketActivity viewModel
    private MarketViewModel viewModel;

    // views
    private TableLayout marketTable;
    private Button backButton;
    private Button tradeButton;
    private HashMap<Good, Integer> trades;
    private HashMap<Good, Integer> tradeTable;
    EditText[] buyAmount;
    EditText[] sellAmount;
    Good[] goods;
    TextView[] goodPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        trades = new HashMap<>();
        marketTable = (TableLayout) findViewById(R.id.market_table);
        backButton = (Button) findViewById(R.id.back_button);
        tradeButton = (Button)findViewById(R.id.execute_trades_button);

        tradeTable = viewModel.loadMarket(Model.getInstance().getGame().getPlayer(),
                Model.getInstance().getGame().getPlayer().getCurrentPlanet());
        TextView[] goodLabels = new TextView[tradeTable.size()];
        TextView[] goodOwned = new TextView[tradeTable.size()];
        goodPrice = new TextView[tradeTable.size()];
        buyAmount = new EditText[tradeTable.size()];
        sellAmount = new EditText[tradeTable.size()];
        TableRow[] newRows = new TableRow[tradeTable.size()];
        goods = (Good[]) tradeTable.keySet().toArray();
        for (int i = 0; i < tradeTable.size(); i++) {
            newRows[i] = new TableRow(this);
            newRows[i].setId(i+1);
            newRows[i].setBackgroundColor(Color.CYAN);
            newRows[i].setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            goodLabels[i] = new TextView(this);
            goodLabels[i].setId(i+111);
            goodLabels[i].setText(goods[i].getType());
            goodLabels[i].setTextColor(Color.BLUE);
            newRows[i].addView(goodLabels[i]);

            goodOwned[i] = new TextView(this);
            goodOwned[i].setId(i+1111);
            goodLabels[i].setTextColor(Color.BLUE);
            goodOwned[i].setText(goods[i].getQuantity());
            newRows[i].addView(goodOwned[i]);

            goodPrice[i] = new TextView(this);
            goodOwned[i].setId(i+11111);
            goodLabels[i].setTextColor(Color.BLUE);
            goodPrice[i].setText(tradeTable.get(goods[i]));
            newRows[i].addView(goodPrice[i]);

            buyAmount[i] = new EditText(this);
            buyAmount[i].setId(i*17+1111);
            buyAmount[i].setHint(0);
            buyAmount[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            buyAmount[i].setBackgroundColor(Color.WHITE);
            newRows[i].addView(buyAmount[i]);

            sellAmount[i] = new EditText(this);
            sellAmount[i].setId(i*13+11);
            sellAmount[i].setHint(0);
            sellAmount[i].setInputType(InputType.TYPE_CLASS_NUMBER);
            sellAmount[i].setBackgroundColor(Color.WHITE);
            newRows[i].addView(sellAmount[i]);

            marketTable.addView(newRows[i], new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

    }

    /**
     * Button handler for the okay button
     *
     * @param view the button that was pressed
     */
    public void onExecutePressed(View view) {
        Log.d("Market", "Execute button pressed");

        for (int i = 0; i < tradeTable.size(); i++) {
            try {
                int buy = Integer.parseInt(buyAmount[i].getText().toString());
                int sell = Integer.parseInt(sellAmount[i].getText().toString());
                int price = Integer.parseInt(goodPrice[i].getText().toString());
                trades.put(new Good(goods[i].getType(), (buy - sell)), price);
            } catch (Exception e) {
                Toast.makeText(this, "Please enter an amount for each good. (Sorry).", Toast.LENGTH_LONG).show();
                return;
            }
        }

        //the data passed in is legal
        //Log.d("testing123", "data passed in is valid: " + name);
//        player = new Player(nameField.getText().toString(), Integer.parseInt(pilotPoints.getText().toString()),
//                            Integer.parseInt(fighterPoints.getText().toString()),
//                            Integer.parseInt(traderPoints.getText().toString()),
//                            Integer.parseInt(engineerPoints.getText().toString()));
//        game = new Game(player, (GameDifficulty) gameDifficultySpinner.getSelectedItem());


        String result = viewModel.processTransactions(trades);
        if (result != null) {
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, UniverseActivity.class);
            startActivity(intent);
        }
    }
    public void onCancelPressed(View view) {
        onBackPressed();
    }

}
