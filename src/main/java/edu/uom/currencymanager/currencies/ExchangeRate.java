package edu.uom.currencymanager.currencies;

public class ExchangeRate {

    private Currency sourceCurrency;
    private Currency destinationCurrency;
    private double rate;
    private long timeLastChecked;

    private ExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate = rate;
        timeLastChecked = System.currentTimeMillis();
    }

    public static ExchangeRate createExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
        return new ExchangeRate(sourceCurrency, destinationCurrency, rate);
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(Currency destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getTimeLastChecked() {
        return timeLastChecked;
    }

    public void setTimeLastChecked(long timeLastChecked) {
        this.timeLastChecked = timeLastChecked;
    }

    public String toString() {
        return sourceCurrency.getCode() + " 1 = " + destinationCurrency.getCode() + " " + Util.formatAmount(rate);
    }

}
