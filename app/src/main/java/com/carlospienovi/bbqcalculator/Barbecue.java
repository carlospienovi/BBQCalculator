package com.carlospienovi.bbqcalculator;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by carlos.pienovi on 12/02/2015.
 */
public class Barbecue implements Parcelable {

    int numberPeople;
    boolean meat, sausage, bloodSausage, provoloneCheese, bread, beverage;

    public Barbecue(int numberPeople, boolean meat, boolean sausage,
                    boolean bloodSausage, boolean provoloneCheese, boolean bread,
                    boolean beverage) {
        this.numberPeople = numberPeople;
        this.meat = meat;
        this.sausage = sausage;
        this.bloodSausage = bloodSausage;
        this.provoloneCheese = provoloneCheese;
        this.bread = bread;
        this.beverage = beverage;
    }

    public Barbecue(Parcel in) {
        boolean[] data = new boolean[6];

        this.setNumberPeople(in.readInt());
        in.readBooleanArray(data);
        this.setMeat(data[0]);
        this.setSausage(data[1]);
        this.setBloodSausage(data[2]);
        this.setProvoloneCheese(data[3]);
        this.setBread(data[4]);
        this.setBeverage(data[5]);
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public boolean getMeat() {
        return meat;
    }

    public boolean getSausage() {
        return sausage;
    }

    public boolean getBloodSausage() {
        return bloodSausage;
    }

    public boolean getProvoloneCheese() {
        return provoloneCheese;
    }

    public boolean getBread() {
        return bread;
    }

    public boolean getBeverage() {
        return beverage;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public void setSausage(boolean sausage) {
        this.sausage = sausage;
    }

    public void setBloodSausage(boolean bloodSausage) {
        this.bloodSausage = bloodSausage;
    }

    public void setProvoloneCheese(boolean provoloneCheese) {
        this.provoloneCheese = provoloneCheese;
    }

    public void setBread(boolean bread) {
        this.bread = bread;
    }

    public void setBeverage(boolean beverage) {
        this.beverage = beverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeInt(this.getNumberPeople());
        destination.writeBooleanArray(
                new boolean[]{
                        this.getMeat(),
                        this.getSausage(),
                        this.getBloodSausage(),
                        this.getProvoloneCheese(),
                        this.getBread(),
                        this.getBeverage()
                });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Barbecue createFromParcel(Parcel in) {
            return new Barbecue(in);
        }

        public Barbecue[] newArray(int size) {
            return new Barbecue[size];
        }
    };
}
