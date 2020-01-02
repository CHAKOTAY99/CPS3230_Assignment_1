package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.RWClass;
import edu.uom.currencymanager.currencyserver.CurrencyServer;

import java.util.HashMap;
import java.util.List;

public class ExchangeRate {

    public Currency sourceCurrency;
    public Currency destinationCurrency;
    public double rate;
    public long timeLastChecked;

//    private static ExchangeRate firstInstance = null;

//    public ExchangeRate() {
//
//    }

    public ExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.rate = rate;
        timeLastChecked = System.currentTimeMillis();
    }

//    public static ExchangeRate getInstance(){
//        if(firstInstance == null){
//            firstInstance = new ExchangeRate();
//        }
//        return firstInstance;
//    }

//    public static ExchangeRate createExchangeRate(Currency sourceCurrency, Currency destinationCurrency, double rate) {
//        return new ExchangeRate(sourceCurrency, destinationCurrency, rate);
//    }

//    @Override
//    public Currency getSourceCurrency() {
//        return sourceCurrency;
//    }
//
//    @Override
//    public void setSourceCurrency(Currency sourceCurrency) {
//        this.sourceCurrency = sourceCurrency;
//    }
//
//    @Override
//    public Currency getDestinationCurrency() {
//        return destinationCurrency;
//    }
//
//    @Override
//    public void setDestinationCurrency(Currency destinationCurrency) {
//        this.destinationCurrency = destinationCurrency;
//    }
//
//    @Override
//    public double getRate() {
//        return rate;
//    }
//
//    @Override
//    public void setRate(double rate) {
//        this.rate = rate;
//    }
//
//    @Override
//    public long getTimeLastChecked() {
//        return timeLastChecked;
//    }
//
//    @Override
//    public void setTimeLastChecked(long timeLastChecked) {
//        this.timeLastChecked = timeLastChecked;
//    }

    public String toString() {
        return sourceCurrency.getCode() + " 1 = " + destinationCurrency.getCode() + " " + Util.formatAmount(rate);
    }

//    @Override
//    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode, List<Currency> currencies) throws Exception {
//        long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100
//        HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();
//        CurrencyDatabase currencyDatabase = new CurrencyDatabase();
//        RWClass rwClass = new RWClass();
//        CurrencyServer currencyServer = rwClass.getCurrencyServer();
//
//        ExchangeRate result = null;
//
//        Currency sourceCurrency = currencyDatabase.getCurrencyByCode(sourceCurrencyCode, currencies);
//        if (sourceCurrency == null) {
//            throw new Exception("Unkown currency: " + sourceCurrencyCode);
//        }
//
//        Currency destinationCurrency = currencyDatabase.getCurrencyByCode(destinationCurrencyCode, currencies);
//        if (destinationCurrency == null) {
//            throw new Exception("Unkown currency: " + destinationCurrencyCode);
//        }
//
//        //Check if exchange rate exists in database
//        String key = sourceCurrencyCode + destinationCurrencyCode;
//        if (exchangeRates.containsKey(key)) {
//            result = exchangeRates.get(key);
//            if (System.currentTimeMillis() - result.getTimeLastChecked() > FIVE_MINUTES_IN_MILLIS) {
//                result = null;
//            }
//        }
//
//        if (result == null) {
//            double rate = currencyServer.getExchangeRate(sourceCurrencyCode, destinationCurrencyCode);
//            result = createExchangeRate(sourceCurrency,destinationCurrency, rate);
//
//            //Cache exchange rate
//            exchangeRates.put(key, result);
//
//            //Cache inverse exchange rate
//            String inverseKey = destinationCurrencyCode+sourceCurrencyCode;
//            exchangeRates.put(inverseKey, createExchangeRate(destinationCurrency, sourceCurrency, 1/rate));
//        }
//
//        return result;
//    }
}