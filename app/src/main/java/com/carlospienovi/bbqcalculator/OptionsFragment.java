package com.carlospienovi.bbqcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class OptionsFragment extends Fragment {

    Button mButtonCalculate;
    EditText mEditTextPeopleNumber;
    Switch mSwitchMeat, mSwitchSausage, mSwitchBloodSausage, mSwitchProvoloneCheese,
            mSwitchBread, mSwitchBeverage;

    public OptionsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_options, container, false);
        init(rootView);
        editTextPeople();
        buttonCalculate();
        return rootView;
    }

    private void editTextPeople() {
        mEditTextPeopleNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mButtonCalculate.setEnabled(!TextUtils.isEmpty(s));
            }
        });
    }

    private void buttonCalculate() {
        mButtonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberPeople = Integer.parseInt(mEditTextPeopleNumber.getText().toString());
                boolean meat = mSwitchMeat.isChecked();
                boolean sausage = mSwitchSausage.isChecked();
                boolean bloodSausage = mSwitchBloodSausage.isChecked();
                boolean provoloneCheese = mSwitchProvoloneCheese.isChecked();
                boolean bread = mSwitchBread.isChecked();
                boolean beverage = mSwitchBeverage.isChecked();

                Barbecue barbecue = new Barbecue(numberPeople, meat, sausage,
                        bloodSausage, provoloneCheese, bread, beverage);

                if (getActivity().findViewById(R.id.container_right) != null) {
                    instantiateFragment(barbecue);
                } else {
                    instantiateActivity(barbecue);
                }
            }

            private void instantiateActivity(Barbecue barbecue) {
                Intent i = new Intent(getActivity(), CalculationActivity.class);
                i.putExtra(CalculationFragment.BARBECUE, barbecue);
                startActivity(i);
            }

            private void instantiateFragment(Barbecue barbecue) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_right, CalculationFragment.newInstance(barbecue))
                        .commit();
            }
        });
    }

    private void init(View rootView) {
        mButtonCalculate = (Button) rootView.findViewById(R.id.button_calculate);
        mEditTextPeopleNumber = (EditText) rootView.findViewById(R.id.edit_text_how_many);
        mSwitchMeat = (Switch) rootView.findViewById(R.id.switch_meat);
        mSwitchSausage = (Switch) rootView.findViewById(R.id.switch_sausage);
        mSwitchBloodSausage = (Switch) rootView.findViewById(R.id.switch_blood_sausage);
        mSwitchProvoloneCheese = (Switch) rootView.findViewById(R.id.switch_provolone_cheese);
        mSwitchBread = (Switch) rootView.findViewById(R.id.switch_bread);
        mSwitchBeverage = (Switch) rootView.findViewById(R.id.switch_beverage);
    }


}
