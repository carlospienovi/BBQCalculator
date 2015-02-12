package com.carlospienovi.bbqcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by carlos.pienovi on 12/02/2015.
 */
public class CustomActivity extends ActionBarActivity {

    private static final String THEME_PREFERENCE = "theme_preference";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme = sharedPreferences.getString(THEME_PREFERENCE, getString(R.string.black));
        setThemeByString(theme);
        super.onCreate(savedInstanceState);
    }

    private void setThemeByString(String theme) {
        if (theme.equals(getString(R.string.red))) {
            setTheme(R.style.AppThemeRed);
        }
        if (theme.equals(getString(R.string.blue))) {
            setTheme(R.style.AppThemeBlue);
        }
        if (theme.equals(getString(R.string.black))) {
            setTheme(R.style.AppThemeBlack);
        }
    }
}
