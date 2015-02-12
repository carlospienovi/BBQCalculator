package com.carlospienovi.bbqcalculator;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by carlos.pienovi on 12/02/2015.
 */
public class EditTextPreference extends android.preference.EditTextPreference {

    public EditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        setSummary(getSummary());
    }

    @Override
    public CharSequence getSummary() {
        return this.getText();
    }
}
