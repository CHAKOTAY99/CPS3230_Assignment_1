package edu.uom.currencymanager.currencies;

import java.util.List;

public interface IExchangeRate {

    public Currency getSourceCurrency();
    public void setSourceCurrency(Currency sourceCurrency);
    public Currency getDestinationCurrency();
    public void setDestinationCurrency(Currency destinationCurrency);
    public double getRate();
    public void setRate(double rate);
    public long getTimeLastChecked();
    public void setTimeLastChecked(long timeLastChecked);
    public String toString();
    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode, List<Currency> currencies) throws  Exception;
}
