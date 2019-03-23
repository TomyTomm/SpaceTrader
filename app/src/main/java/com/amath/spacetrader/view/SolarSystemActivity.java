package com.amath.spacetrader.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amath.spacetrader.entity.Planet;

public class SolarSystemActivity extends AppCompatActivity {

    public void onPlanetPressed(View view) {
        Planet planet = (Planet) view.getTag();

        Intent intent = new Intent(this, PlanetActivity.class);
        intent.putExtra("Planet", planet);
        startActivity(intent);
    }
}
