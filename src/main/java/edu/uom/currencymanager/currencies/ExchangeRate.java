package edu.uom.currencymanager.currencies;

import javax.swing.text.NumberFormatter;

public class ExchangeRate {

    public Currency sourceCurrency;
    public Currency destinationCurrency;
    public double rate;
    public long timeLastChecked;

    public ExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
        this.sourceCurrency =sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate =rate;
        timeLastChecked = System.currentTimeMillis();
    }

    public String toString() {
        return sourceCurrency.code + " 1 = " + destinationCurrency.code + " " + Util.formatAmount(rate);
    }

}
