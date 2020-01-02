package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyDatabaseTest {

    CurrencyDatabase currencyDatabase;
    ExchangeRate exchangeRate;

    @Before
    public void setup() throws Exception {
        currencyDatabase = new CurrencyDatabase();
        // Clean Database
        List<String> currencyCodes = new ArrayList<>();
        List<Currency> initialDatabase = currencyDatabase.getCurrencies();
        for(Currency currency : initialDatabase){
            currencyCodes.add(currency.getCode());
        }
        for(String currencyCode : currencyCodes){
            currencyDatabase.deleteCurrency(currencyCode);
        }
    }

    @After
    public void teardown() throws Exception{
        currencyDatabase.addCurrency(Currency.createCurrency("AUD", "Australian Dollar", false));
        currencyDatabase.addCurrency(Currency.createCurrency("EUR", "Euro", true));
        currencyDatabase.addCurrency(Currency.createCurrency("GBP", "British Pound", true));
        currencyDatabase.addCurrency(Currency.createCurrency("TRY", "Turkish Lira", false));
        currencyDatabase.addCurrency(Currency.createCurrency("USD", "US Dollar", true));
        currencyDatabase = null;
    }

    @Test
    public void testCurrencyByCode_NotExist() {
        // Exercise
        List<Currency> currencies = currencyDatabase.getCurrencies();
        Currency currency = currencyDatabase.getCurrencyByCode(null, currencies);
        // Verify
        assertEquals(null, currency);
    }

    @Test
    public void testCurrencyByCode_Default() throws Exception {
        // Setup
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));
        // Exercise
        List<Currency> currencies = currencyDatabase.getCurrencies();
        Currency currency = currencyDatabase.getCurrencyByCode("RMB", currencies);
        // Verify
        assertEquals("RMB", currency.getCode());
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testCurrencyExists_ReturnFalse() {
        // Exercise
        List<Currency> currencies = currencyDatabase.getCurrencies();
        boolean check = currencyDatabase.currencyExists("RMB", currencies);
        // Verify
        assertEquals(false, check);
    }

    @Test
    public void testCurrencyExists_ReturnTrue() throws Exception {
        // Setup
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));
        // Exercise
        List<Currency> currencies = currencyDatabase.getCurrencies();
        boolean check = currencyDatabase.currencyExists("RMB", currencies);
        // Verify
        assertEquals(check, true);
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetCurrencies_ReturnPopulatedList() throws Exception {
        // Setup
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));
        // Exercise
        List<Currency> testList = currencyDatabase.getCurrencies();
        // Verify
        assertTrue(testList.size() > 0);
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetCurrencies_ReturnEmptyList() throws Exception {
        // Exercise
        List<Currency> testList = currencyDatabase.getCurrencies();
        // Verify
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testGetMajorCurrencies_ReturnPopulatedList() throws Exception {
        // Setup
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));
        // Exercise
        List<Currency> testList = currencyDatabase.getMajorCurrencies();
        // Verify
        assertTrue(testList.size() > 0);
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetMajorCurrencies_ReturnEmptyDefaultList() throws Exception {
        // Setup - added non major currency
        currencyDatabase.addCurrency(Currency.createCurrency("EGP", "Egyptian Pound", false));
        // Exercise
        List<Currency> testList = currencyDatabase.getMajorCurrencies();
        // Verify
        assertTrue(testList.isEmpty());
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
    }

    @Test
    public void testGetExchangeRate_ExceptionUnknownSource() throws Exception {
        // Setup - added non major currency
        currencyDatabase.addCurrency(Currency.createCurrency("EGP", "Egyptian Pound", false));
        List<Currency> currencies = currencyDatabase.getCurrencies();
        // Exercise
        try {
            exchangeRate.getExchangeRate("RMB", "EGP", currencies);
        } catch (Exception exception) {
            assertEquals("Unkown currency: RMB", exception.getMessage());
        }
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
    }

    @Test
    public void testGetExchangeRate_ExceptionUnknownDestination() throws Exception {
        // Setup - added non major currency
        currencyDatabase.addCurrency(Currency.createCurrency("EGP", "Egyptian Pound", false));
        List<Currency> currencies = currencyDatabase.getCurrencies();
        // Exercise
        try {
            exchangeRate.getExchangeRate("EGP", "RMB", currencies);
        } catch (Exception exception) {
            assertEquals("Unkown currency: RMB", exception.getMessage());
        }
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
    }

    @Test
    public void testGetExchangeRate_ReturnRate() throws Exception {
        // Setup
        Currency currency1 = Currency.createCurrency("EGP", "Egyptian Pound", false);
        Currency currency2 = Currency.createCurrency("RMB", "Renminbi", true);
        currencyDatabase.addCurrency(currency1);
        currencyDatabase.addCurrency(currency2);
        ExchangeRate exchangeRate = ExchangeRate.createExchangeRate(currency1, currency2, 0.52);
        List<Currency> currencies = currencyDatabase.getCurrencies();
        // Exercise
        ExchangeRate result = exchangeRate.getExchangeRate("EGP", "RMB", currencies);
        result.setRate(0.52);
        // Verify
        assertEquals("EGP 1 = RMB " + exchangeRate.getRate() + "", result.toString());
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetExchangeRate_TimeExceeded() throws Exception {
        // Setup
        Currency currency1 = Currency.createCurrency("EGP", "Egyptian Pound", false);
        Currency currency2 = Currency.createCurrency("RMB", "Renminbi", true);
        currencyDatabase.addCurrency(currency1);
        currencyDatabase.addCurrency(currency2);
        List<Currency> currencies = currencyDatabase.getCurrencies();
        ExchangeRate result = exchangeRate.getExchangeRate("EGP", "RMB", currencies);
        result.setTimeLastChecked(result.getTimeLastChecked() - 600000);
        // Exercise
        ExchangeRate result2 = exchangeRate.getExchangeRate("EGP", "RMB", currencies);
        // Verify
        assertTrue(result.getRate() != result2.getRate());
        // TearDown
        currencyDatabase.deleteCurrency("EGP");
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testAddCurrency() throws Exception {
        // Exercise
        currencyDatabase.addCurrency(Currency.createCurrency("EGP", "Egyptian Pound", false));
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));
        // Verify
        List<Currency> testList = currencyDatabase.getCurrencies();
        assertEquals(2, testList.size());
        // TearDown
        currencyDatabase.deleteCurrency("EGP");
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        // Setup
        currencyDatabase.addCurrency(Currency.createCurrency("EGP", "Egyptian Pound", false));
        currencyDatabase.addCurrency(Currency.createCurrency("RMB", "Renminbi", true));

        List<Currency> testListStart = currencyDatabase.getCurrencies();
        // Exercise
        currencyDatabase.deleteCurrency("EGP");
        // Verify
        assertEquals(1, testListStart.size());
        // TearDown
        currencyDatabase.deleteCurrency("RMB");
    }
}