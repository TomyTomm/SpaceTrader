package com.amath.spacetrader.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.amath.spacetrader.R;

public class PlanetActivity extends AppCompatActivity {

    public View getRow() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.planet_row, null);
        return view;
    }
}