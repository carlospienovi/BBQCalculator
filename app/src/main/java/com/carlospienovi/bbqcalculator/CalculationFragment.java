package com.carlospienovi.bbqcalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by carlos.pienovi on 12/02/2015.
 */
public class CalculationFragment extends Fragment {

    private static final int DECIMAL_FIGURES = 2;
    private static final double MEAT_PER_PERSON = 0.35;
    private static final double SAUSAGE_PER_PERSON = 0.1;
    private static final double PROVOLONE_PER_PERSON = 0.05;
    private static final double BREAD_PER_PERSON = 0.3;
    private static final double LT_BEVERAGE_PER_PERSON = 1;

    public static final String NUMBER_PEOPLE = "number_people";
    public static final String MEAT = "meat";
    public static final String SAUSAGE = "sausage";
    public static final String BLOOD_SAUSAGE = "blood_sausage";
    public static final String PROVOLONE_CHEESE = "provolone_cheese";
    public static final String BREAD = "bread";
    public static final String BEVERAGE = "beverage";

    private static final String MEAT_PRICE = "meat_preference";
    private static final String SAUSAGE_PRICE = "sausage_preference";
    private static final String BLOOD_SAUSAGE_PRICE = "blood_sausage_preference";
    private static final String PROVOLONE_CHEESE_PRICE = "provolone_cheese_preference";
    private static final String BREAD_PRICE = "bread_preference";
    private static final String BEVERAGE_PRICE = "beverage_preference";

    boolean meat, sausage, bloodSausage, provoloneCheese, bread, beverage;
    int numberPeople;
    double meatPrice, sausagePrice, bloodSausagePrice, provoloneCheesePrice,
            breadPrice, beveragePrice;

    TextView mTextViewTitle, mTextViewResultMeat, mTextViewResultSausage,
            mTextViewResultBloodSausage, mTextViewResultProvoloneCheese,
            mTextViewResultBread, mTextViewResultBeverage, mTextViewTotal;

    public CalculationFragment() {
    }

    public static CalculationFragment newInstance(int people, boolean meat, boolean sausage,
                                                  boolean bloodSausage, boolean provoloneCheese,
                                                  boolean bread, boolean beverage) {
        Bundle bundle = new Bundle();
        bundle.putInt(NUMBER_PEOPLE, people);
        bundle.putBoolean(MEAT, meat);
        bundle.putBoolean(SAUSAGE, sausage);
        bundle.putBoolean(BLOOD_SAUSAGE, bloodSausage);
        bundle.putBoolean(PROVOLONE_CHEESE, provoloneCheese);
        bundle.putBoolean(BREAD, bread);
        bundle.putBoolean(BEVERAGE, beverage);
        CalculationFragment f = new CalculationFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculation, container, false);
        init(rootView);
        calculateBBQ();
        return rootView;
    }

    private void init(View rootView) {
        mTextViewTitle = (TextView) rootView.findViewById(R.id.text_view_result_title);
        mTextViewResultMeat = (TextView) rootView.findViewById(R.id.text_view_result_meat);
        mTextViewResultSausage = (TextView) rootView.findViewById(R.id.text_view_result_sausage);
        mTextViewResultBloodSausage = (TextView) rootView.findViewById(R.id.text_view_result_blood_sausage);
        mTextViewResultProvoloneCheese = (TextView) rootView.findViewById(R.id.text_view_result_provolone_cheese);
        mTextViewResultBread = (TextView) rootView.findViewById(R.id.text_view_result_bread);
        mTextViewResultBeverage = (TextView) rootView.findViewById(R.id.text_view_result_beverage);
        mTextViewTotal = (TextView) rootView.findViewById(R.id.text_view_total_amount);
    }

    private void calculateBBQ() {
        getBundle();
        getPrices();
        updateTextViews();
    }

    private void updateTextViews() {
        mTextViewTitle.setText(String.format
                (getString(R.string.text_view_result_title),
                        numberPeople));
        if (meat) {
            mTextViewResultMeat.setVisibility(View.VISIBLE);
            mTextViewResultMeat.setText(String.format
                    (getString(R.string.text_view_result_meat),
                            round((numberPeople * MEAT_PER_PERSON))));
        } else {
            mTextViewResultMeat.setVisibility(View.GONE);
        }
        if (sausage) {
            mTextViewResultSausage.setVisibility(View.VISIBLE);
            mTextViewResultSausage.setText(String.format
                    (getString(R.string.text_view_result_sausage),
                            round((numberPeople * SAUSAGE_PER_PERSON))));
        } else {
            mTextViewResultSausage.setVisibility(View.GONE);
        }

        if (bloodSausage) {
            mTextViewResultBloodSausage.setVisibility(View.VISIBLE);
            mTextViewResultBloodSausage.setText(String.format
                    (getString(R.string.text_view_result_blood_sausage),
                            round((numberPeople * SAUSAGE_PER_PERSON))));
        } else {
            mTextViewResultBloodSausage.setVisibility(View.GONE);
        }

        if (provoloneCheese) {
            mTextViewResultProvoloneCheese.setVisibility(View.VISIBLE);
            mTextViewResultProvoloneCheese.setText(String.format
                    (getString(R.string.text_view_result_provolone_cheese),
                            round((numberPeople * PROVOLONE_PER_PERSON))));
        } else {
            mTextViewResultProvoloneCheese.setVisibility(View.GONE);
        }

        if (bread) {
            mTextViewResultBread.setVisibility(View.VISIBLE);
            mTextViewResultBread.setText(String.format
                    (getString(R.string.text_view_result_bread), (int) (numberPeople * BREAD_PER_PERSON)));
        } else {
            mTextViewResultBread.setVisibility(View.GONE);
        }

        if (beverage) {
            mTextViewResultBeverage.setVisibility(View.VISIBLE);
            mTextViewResultBeverage.setText(String.format
                    (getString(R.string.text_view_result_beverage), (numberPeople * LT_BEVERAGE_PER_PERSON)));
        } else {
            mTextViewResultBeverage.setVisibility(View.GONE);
        }

        mTextViewTotal.setText(String.format
                (getString(R.string.text_view_total_amount), getTotal()));
    }

    private double getTotal() {
        double totalMeat = 0, totalSausage = 0, totalBloodSausage = 0,
                totalProvoloneCheese = 0, totalBread = 0, totalBeverage = 0;
        if (meat) totalMeat = numberPeople * MEAT_PER_PERSON * meatPrice;
        if (sausage) totalSausage = numberPeople * SAUSAGE_PER_PERSON * sausagePrice;
        if (bloodSausage) totalBloodSausage = numberPeople * SAUSAGE_PER_PERSON * bloodSausagePrice;
        if (provoloneCheese)
            totalProvoloneCheese = numberPeople * PROVOLONE_PER_PERSON * provoloneCheesePrice;
        if (bread) totalBread = numberPeople * BREAD_PER_PERSON * breadPrice;
        if (beverage) totalBeverage = numberPeople * LT_BEVERAGE_PER_PERSON * beveragePrice;
        double sum = totalMeat + totalSausage + totalBloodSausage +
                totalProvoloneCheese + totalBread + totalBeverage;
        return round(sum);
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            numberPeople = bundle.getInt(NUMBER_PEOPLE);
            meat = bundle.getBoolean(MEAT);
            sausage = bundle.getBoolean(SAUSAGE);
            bloodSausage = bundle.getBoolean(BLOOD_SAUSAGE);
            provoloneCheese = bundle.getBoolean(PROVOLONE_CHEESE);
            bread = bundle.getBoolean(BREAD);
            beverage = bundle.getBoolean(BEVERAGE);
        }
    }

    private void getPrices() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultValue = getString(R.string.default_price_value);
        meatPrice = Double.parseDouble(sharedPreferences.getString(MEAT_PRICE, defaultValue));
        sausagePrice = Double.parseDouble(sharedPreferences.getString(SAUSAGE_PRICE, defaultValue));
        bloodSausagePrice = Double.parseDouble(sharedPreferences.getString(BLOOD_SAUSAGE_PRICE, defaultValue));
        provoloneCheesePrice = Double.parseDouble(sharedPreferences.getString(PROVOLONE_CHEESE_PRICE, defaultValue));
        breadPrice = Double.parseDouble(sharedPreferences.getString(BREAD_PRICE, defaultValue));
        beveragePrice = Double.parseDouble(sharedPreferences.getString(BEVERAGE_PRICE, defaultValue));
    }

    private double round(double value) {
        if (DECIMAL_FIGURES < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(DECIMAL_FIGURES, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
