package com.carlospienovi.bbqcalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class CalculationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        if (savedInstanceState == null) {
            Intent i = getIntent();
            int people = i.getIntExtra(CalculationFragment.NUMBER_PEOPLE, -1);
            boolean meat = i.getBooleanExtra(CalculationFragment.MEAT, true);
            boolean sausage = i.getBooleanExtra(CalculationFragment.SAUSAGE, false);
            boolean bloodSausage = i.getBooleanExtra(CalculationFragment.BLOOD_SAUSAGE, false);
            boolean provoloneCheese = i.getBooleanExtra(CalculationFragment.PROVOLONE_CHEESE, false);
            boolean bread = i.getBooleanExtra(CalculationFragment.BREAD, false);
            boolean beverage = i.getBooleanExtra(CalculationFragment.BEVERAGE, true);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_left, CalculationFragment.newInstance
                            (people, meat, sausage, bloodSausage,
                                    provoloneCheese, bread, beverage))
                    .commit();
        }
    }

}
