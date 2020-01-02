package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.RWClass;
import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyDatabase implements ICurrencyDatabase{
    public RWClass rwClass;
    public CurrencyServer currencyServer;
    public List<Currency> currencies = new ArrayList<Currency>();
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();


    public CurrencyDatabase() throws Exception {

    }

    @Override
    public void retreiveData() throws Exception {
        rwClass = new RWClass();
        currencies = rwClass.readExistingFile();
    }

    @Override
    public Currency getCurrencyByCode(String code, List<Currency> currencies) {

        for (Currency currency : currencies) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }

        return null;
    }

    @Override
    public boolean currencyExists(String code, List<Currency> currencies) {
        return getCurrencyByCode(code, currencies) != null;
    }

    @Override
    public List<Currency> getCurrencies() {
        return currencies;
    }

    @Override
    public List<Currency> getMajorCurrencies() {
        List<Currency> result = new ArrayList<Currency>();

        for (Currency currency : currencies) {
            if (currency.isMajor()) {
                result.add(currency);
            }
        }

        return result;
    }

    public ExchangeRate getExchangeRate(String sourceCurrencyCode, String destinationCurrencyCode) throws  Exception {
        long FIVE_MINUTES_IN_MILLIS = 300000;  //5*60*100

        ExchangeRate result = null;

        Currency sourceCurrency = getCurrencyByCode(sourceCurrencyCode, currencies);
        if (sourceCurrency == null) {
            throw new Exception("Unkown currency: " + sourceCurrencyCode);
        }

        Currency destinationCurrency = getCurrencyByCode(destinationCurrencyCode, currencies);
        if (destinationCurrency == null) {
            throw new Exception("Unkown currency: " + destinationCurrencyCode);
        }

        //Check if exchange rate exists in database
        String key = sourceCurrencyCode + destinationCurrencyCode;
        if (exchangeRates.containsKey(key)) {
            result = exchangeRates.get(key);
            if (System.currentTimeMillis() - result.timeLastChecked > FIVE_MINUTES_IN_MILLIS) {
                result = null;
            }
        }

        if (result == null) {
            double rate = currencyServer.getExchangeRate(sourceCurrencyCode, destinationCurrencyCode);
            result = new ExchangeRate(sourceCurrency,destinationCurrency, rate);

            //Cache exchange rate
            exchangeRates.put(key, result);

            //Cache inverse exchange rate
            String inverseKey = destinationCurrencyCode+sourceCurrencyCode;
            exchangeRates.put(inverseKey, new ExchangeRate(destinationCurrency, sourceCurrency, 1/rate));
        }

        return result;
    }


    @Override
    public void addCurrency(Currency currency) throws Exception {

        //Save to list
        currencies.add(currency);

        // Write to file
        rwClass.writeToFile(currencies);
    }

    @Override
    public void deleteCurrency(String code) throws Exception {

        //Save to list
        currencies.remove(getCurrencyByCode(code, currencies));

        // Write to flie
        rwClass.writeToFile(currencies);
    }
}
