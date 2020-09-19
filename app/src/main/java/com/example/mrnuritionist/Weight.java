package com.example.mrnuritionist;

import java.io.Serializable;

public class Weight implements Serializable {

    private double _weight;
    private long _date;

    public Weight(long date, double weight) {
        this._weight = weight;
        this._date = date;
    }

    public void convertToLbs(){
        _weight = Calc.KgToPound(_weight);
    }

    public void convertToKgs(){
        _weight = Calc.PoundToKg(_weight);
    }

    public double get_weight() {
        return _weight;
    }



}
