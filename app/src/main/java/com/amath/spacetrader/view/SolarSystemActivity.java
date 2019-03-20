package com.amath.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.amath.spacetrader.R;
import com.amath.spacetrader.viewmodel.SolarSystemViewModel;
import com.amath.spacetrader.viewmodel.UniverseViewModel;

public class SolarSystemActivity extends AppCompatActivity {

    SolarSystemViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solarsystem);

        viewModel = ViewModelProviders.of(this).get(SolarSystemViewModel.class);

    }
}
