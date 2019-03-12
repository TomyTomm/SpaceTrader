package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.amath.spacetrader.R;
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

        boolean savedGame = true;
        File file = new File(this.getFilesDir(), "game.txt");
        try {
            viewModel.loadLocalGame(file);
//        } catch (FileNotFoundException e) {
//            savedGame = false;
//
        } catch (Exception e) {
            Log.d("filemissingerror", e.getMessage());
            savedGame = false;
            try {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();


                //Look to FireBase Authentication first, if no information, try
                // pulling information from local repo
                if (currentUser != null) { //There is currently a user signed in
                    Log.d("user", currentUser.toString());
                } else {
                    Log.d("user", "No user information available from FireBase");
                    savedGame = false;

                }
            } catch (Exception ef) {
                savedGame = false;
                Log.d("FIREBASE ERROR", String.valueOf(ef.getStackTrace()));

            }
        }

        if (savedGame) {
            Log.d("savedgame", "REALLY? NO WAY I HONESTLY DON'T BELIEVE YOU");
            Log.d("savedgame", Model.getInstance().getPlayer().toString());
            Intent intent = new Intent(this, UniverseActivity.class);
            startActivity(intent);
        } else {
            config();
        }

//        Game testObject = new Game();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Player");
//
//
////        testObject.changeDifficulty(GameDifficulty.DIFFICULT);
//        myRef.setValue(testObject.getPlayer());
//
//        myRef = database.getReference("test");
//
//        myRef.setValue("YAY");



        //Example code for how to set data value


        //Example code for listening to a value. (Not completely used in this game)
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("test", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("test", "Failed to read value.", error.toException());
//            }
//        });
    }

    /**
     * Button handler for the start button
     * will be replaced with calling the repository to load game
     *
     * @param view the button that was pressed
     */
    public void onOkayPressed(View view) {
        config();
    }

    private void config() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }
}
