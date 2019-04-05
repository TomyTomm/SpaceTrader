package com.amath.spacetrader.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amath.spacetrader.R;
import com.amath.spacetrader.entity.RandomEvent;
import com.amath.spacetrader.viewmodel.PlanetViewModel;
import com.amath.spacetrader.viewmodel.RandomEventViewModel;

public class RandomEventActivity extends AppCompatActivity {

    private RandomEventViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomevent);

        viewModel = ViewModelProviders.of(this).get(RandomEventViewModel.class);

        Bundle extras = getIntent().getExtras();
        RandomEvent randomEvent = (RandomEvent) extras.get("randomEvent");

        TextView randomEventName = findViewById(R.id.random_event_name);
        randomEventName.setText(randomEvent.name());

        TextView randomEventDescription = findViewById(R.id.random_event_description);
        randomEventDescription.setText(randomEvent.getDescription());

        TextView randomEventResult = findViewById(R.id.random_event_result);
        randomEventResult.setText(viewModel.executeEvent(randomEvent));

        TextView randomEventRemainingInventory = findViewById(R.id.random_event_updated_inventory);
        randomEventRemainingInventory.setText(viewModel.executeResultEvent(randomEvent));
    }

    public void onDonePressed(View view) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    protected void onBackPressed(View view) {
        onBackPressed();
        viewModel.toString();
    }
}
