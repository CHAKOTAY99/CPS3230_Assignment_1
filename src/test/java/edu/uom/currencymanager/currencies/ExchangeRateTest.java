package edu.uom.currencymanager.currencies;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeRateTest {

    @Test
    public void constructorTest(){
        // Setup
        Currency sourceCurrency = new Currency("EGP", "Egyptian Pound", false);
        Currency destinationCurrency = new Currency("RMB", "Rembini", true);
        double rate = 1.0;
        long timeLastChecked = System.currentTimeMillis();
        // Exercise
        ExchangeRate exchangeRate = new ExchangeRate(sourceCurrency, destinationCurrency, rate);
        // Verify
        assertTrue(Math.abs(exchangeRate.rate-rate) <= 10);
        assertTrue(Math.abs(exchangeRate.timeLastChecked-timeLastChecked)<=10);
        assertEquals(exchangeRate.sourceCurrency, sourceCurrency);
        assertEquals(exchangeRate.destinationCurrency, destinationCurrency);
    }


    @Test
    public void testToString_returnValid(){
        // Setup
        Currency sourceCurrency = new Currency("EGP", "Egyptian Pound", false);
        Currency destinationCurrency = new Currency("RMB", "Rembini", true);
        double rate = 1;
        // Exercise
        ExchangeRate exchangeRate = new ExchangeRate(sourceCurrency, destinationCurrency, rate);
        String result = exchangeRate.toString();
        assertEquals("EGP 1 = RMB 1.00", result);
    }
}
