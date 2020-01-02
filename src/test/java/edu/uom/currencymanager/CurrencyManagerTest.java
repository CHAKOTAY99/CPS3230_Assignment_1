package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.ExchangeRate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CurrencyManagerTest {
    CurrencyManager currencyManager;

    @Before
    public void setup() throws Exception {
        currencyManager = new CurrencyManager();
        // Clean Database
        List<String> currencyCodes = new ArrayList<>();
        List<Currency> initialDatabase = currencyManager.currencyDatabase.getCurrencies();
        for(Currency currency : initialDatabase){
            currencyCodes.add(currency.getCode());
        }
        for(String currencyCode : currencyCodes){
            currencyManager.currencyDatabase.deleteCurrency(currencyCode);
        }
    }

    @After
    public void teardown() throws Exception{
        currencyManager.addCurrency("AUD", "Australian Dollar", false);
        currencyManager.addCurrency("EUR", "Euro", true);
        currencyManager.addCurrency("GBP", "British Pound", true);
        currencyManager.addCurrency("TRY", "Turkish Lira", false);
        currencyManager.addCurrency("USD", "US Dollar", true);
        currencyManager = null;
    }

    @Test
    public void testGetMajorCurrencyRates_ReturnList() throws Exception{
        currencyManager.addCurrency("RMB", "Renmbini", true);
        currencyManager.addCurrency("USD", "United States Dollar", true);
        currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        List<ExchangeRate> exchangeRates = currencyManager.getMajorCurrencyRates();
        assertEquals(2, exchangeRates.size());
        // Teardown
        currencyManager.deleteCurrencyWithCode("RMB");
        currencyManager.deleteCurrencyWithCode("USD");
        currencyManager.deleteCurrencyWithCode("EGP");
    }

    @Test
    public void testGetExchangeRate_ReturnRate() throws Exception {
        // Setup
        currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        currencyManager.addCurrency("RMB", "Renminbi", true);
        List<Currency> currencies = currencyManager.currencyDatabase.getCurrencies();
        Currency currency1 = currencyManager.currencyDatabase.getCurrencyByCode("EGP", currencies);
        Currency currency2 = currencyManager.currencyDatabase.getCurrencyByCode("RMB", currencies);
        // Exercise
        ExchangeRate result = currencyManager.getExchangeRate("EGP", "RMB", currencies);
        // Verify
        assertEquals(currency1, result.sourceCurrency);
        assertEquals(currency2, result.destinationCurrency);

        // Teardown
        currencyManager.deleteCurrencyWithCode("EGP");
        currencyManager.deleteCurrencyWithCode("RMB");
    }

    @Test
    public void testAddCurrency_throwExceptionNotThreeCharSmall() throws Exception{
        // Exercise and verify
        try {
        currencyManager.addCurrency("EG", "Egyptian Pound", false);
        } catch(Exception exception){
            assertEquals("A currency code should have 3 characters.", exception.getMessage());
        }
    }

    @Test
    public void testAddCurrency_throwExceptionNotThreeCharLarge() throws Exception{
        // Exercise and verify
        try {
            currencyManager.addCurrency("EGPA", "Egyptian Pound", false);
        } catch(Exception exception){
            assertEquals("A currency code should have 3 characters.", exception.getMessage());
        }
    }

    @Test
    public void testAddCurrency_throwExceptionNameMinimumNotMet() throws Exception{
        // Exercise and verify
        try {
            currencyManager.addCurrency("EGP", "E", false);
        } catch(Exception exception){
            assertEquals("A currency's name should be at least 4 characters long.", exception.getMessage());
        }
    }

    @Test
    public void testAddCurrency_throwExceptionCurrencyAlreadyExists() throws Exception{
        // Setup
        currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        // Exercise and verify
        try {
            currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        } catch(Exception exception){
            assertEquals("The currency EGP already exists.", exception.getMessage());
        }
        // Teardown
        currencyManager.deleteCurrencyWithCode("EGP");
    }

    @Test
    public void testAddCurrency_Succeeds() throws Exception{
        // Excrcise
        currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        // Verify
        List<Currency> currencies = currencyManager.currencyDatabase.getCurrencies();
        Currency result = currencyManager.currencyDatabase.getCurrencyByCode("EGP", currencies);
        assertEquals("EGP", result.getCode());
        assertEquals("Egyptian Pound", result.getName());
        assertEquals(false, result.isMajor());
    }

    @Test
    public void testDeleteCurrency_ExceptionDoesNotExist() throws Exception{
        // Exercise and Verify
        try{
            currencyManager.deleteCurrencyWithCode("EGP");
        } catch(Exception exception){
            assertEquals("Currency does not exist: EGP", exception.getMessage());
        }
    }

    @Test
    public void testDeleteCurrency_SuccessWithException() throws Exception{
        // Setup
        currencyManager.addCurrency("EGP", "Egyptian Pound", false);
        // Exercise
        currencyManager.deleteCurrencyWithCode("EGP");
        // Verify
        try{
            currencyManager.deleteCurrencyWithCode("EGP");
        } catch(Exception exception){
            assertEquals("Currency does not exist: EGP", exception.getMessage());
        }
    }
}