package edu.uom.currencymanager.currencyserver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DefaultCurrencyServerTest {

    @Test
    public void test_getExchangeRateResult(){
        // Setup
        DefaultCurrencyServer defaultCurrencyServer = new DefaultCurrencyServer();
        String sourceCurrency = "RMB", destinationCurrency = "EGP";
        // Exercise
        double result = defaultCurrencyServer.getExchangeRate(sourceCurrency, destinationCurrency);
        assertTrue(result < 1.5 && result > 0);
    }
}
