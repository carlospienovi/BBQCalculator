package com.carlospienovi.bbqcalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class CalculationActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        if (savedInstanceState == null) {
            Barbecue barbecue = getIntent().getParcelableExtra(CalculationFragment.BARBECUE);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_left, CalculationFragment.newInstance(barbecue))
                    .commit();
        }
    }

}
