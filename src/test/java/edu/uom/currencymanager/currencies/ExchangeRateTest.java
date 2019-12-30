package edu.uom.currencymanager.currencies;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeRateTest {

    @Test
    public void constructorTest(){
        // Setup
        Currency sourceCurrency = Currency.createCurrency("EGP", "Egyptian Pound", false);
        Currency destinationCurrency = Currency.createCurrency("RMB", "Rembini", true);
        double rate = 1.0;
        long timeLastChecked = System.currentTimeMillis();
        // Exercise
        ExchangeRate exchangeRate = ExchangeRate.createExchangeRate(sourceCurrency, destinationCurrency, rate);
        // Verify
        assertTrue(Math.abs(exchangeRate.getRate()-rate) <= 10);
        assertTrue(Math.abs(exchangeRate.getTimeLastChecked()-timeLastChecked) <= 10);
        assertEquals(exchangeRate.getSourceCurrency(), sourceCurrency);
        assertEquals(exchangeRate.getDestinationCurrency(), destinationCurrency);
    }


    @Test
    public void testToString_returnValid(){
        // Setup
        Currency sourceCurrency = Currency.createCurrency("EGP", "Egyptian Pound", false);
        Currency destinationCurrency = Currency.createCurrency("RMB", "Rembini", true);
        double rate = 1;
        // Exercise
        ExchangeRate exchangeRate = ExchangeRate.createExchangeRate(sourceCurrency, destinationCurrency, rate);
        String result = exchangeRate.toString();
        assertEquals("EGP 1 = RMB 1.00", result);
    }
}
