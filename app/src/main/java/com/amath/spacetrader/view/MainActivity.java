package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.Constants;
import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.viewmodel.ConfigurationViewModel;
import com.amath.spacetrader.viewmodel.MainViewModel;
import com.amath.spacetrader.viewmodel.MarketViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        File file = new File(this.getFilesDir(), Constants.LOCAL_GAME_SERIALIZATION_FILE);

        if (viewModel.loadLocalGame(file)) {
            Log.i("mainActivity", "Game loaded successfully. ");
            Intent intent = new Intent(this, PlayerActivity.class);
            startActivity(intent);
        } else {
            Log.d("mainActivity", "Load unsuccessful. Starting new game.");
            Intent intent = new Intent(this, ConfigurationActivity.class);
            startActivity(intent);
        }


    }

    /**
     * Button handler for the start button
     * will be replaced with calling the repository to load game
     *
     * @param view the button that was pressed
     */
    public void onOkayPressed(View view) {
        Log.i("mainActivity", "Ok was pressed");
    }
}
