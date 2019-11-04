package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyDatabaseTest {

    CurrencyDatabase currencyDatabase;

    @Before
    public void setup() throws Exception {
        currencyDatabase = new CurrencyDatabase();
        // Clean Database
        List<String> currencyCodes = new ArrayList<>();
        List<Currency> initialDatabase = currencyDatabase.getCurrencies();
        for(Currency currency : initialDatabase){
            currencyCodes.add(currency.code);
        }
        for(String currencyCode : currencyCodes){
            currencyDatabase.deleteCurrency(currencyCode);
        }
    }

    @After
    public void teardown() {
        currencyDatabase = null;
    }

    @Test
    public void testCurrencyByCode_NotExist() {
        // Exercise
        Currency currency = currencyDatabase.getCurrencyByCode(null);
        // Verify
        assertEquals(null, currency);
    }

    @Test
    public void testCurrencyByCode_Default() throws Exception {
        // Setup
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));
        // Exercise
        Currency currency = currencyDatabase.getCurrencyByCode("RMB");
        // Verify
        assertEquals("RMB", currency.code);
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testCurrencyExists_ReturnFalse() {
        // Exercise
        boolean check = currencyDatabase.currencyExists("RMB");
        // Verify
        assertEquals(false, check);
    }

    @Test
    public void testCurrencyExists_ReturnTrue() throws Exception {
        // Setup
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));
        // Exercise
        boolean check = currencyDatabase.currencyExists("RMB");
        // Verify
        assertEquals(check, true);
        // Teardown
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetCurrencies_ReturnPopulatedList() throws Exception {
        // Setup
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));
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
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));
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
        currencyDatabase.addCurrency(new Currency("EGP", "Egyptian Pound", false));
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
        currencyDatabase.addCurrency(new Currency("EGP", "Egyptian Pound", false));
        // Exercise
        try {
            currencyDatabase.getExchangeRate("RMB", "EGP");
        } catch (Exception exception) {
            assertEquals("Unkown currency: RMB", exception.getMessage());
        }
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
    }

    @Test
    public void testGetExchangeRate_ExceptionUnknownDestination() throws Exception {
        // Setup - added non major currency
        currencyDatabase.addCurrency(new Currency("EGP", "Egyptian Pound", false));
        // Exercise
        try {
            currencyDatabase.getExchangeRate("EGP", "RMB");
        } catch (Exception exception) {
            assertEquals("Unkown currency: RMB", exception.getMessage());
        }
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
    }

    @Test
    public void testGetExchangeRate_ReturnRate() throws Exception {
        // Setup
        Currency currency1 = new Currency("EGP", "Egyptian Pound", false);
        Currency currency2 = new Currency("RMB", "Renminbi", true);
        currencyDatabase.addCurrency(currency1);
        currencyDatabase.addCurrency(currency2);
        ExchangeRate exchangeRate = new ExchangeRate(currency1, currency2, 0.52);
        // Exercise
        ExchangeRate result = currencyDatabase.getExchangeRate("EGP", "RMB");
        result.rate = 0.52;
        // Verify
        assertEquals("EGP 1 = RMB " + exchangeRate.rate + "", result.toString());
        // Teardown
        currencyDatabase.deleteCurrency("EGP");
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testGetExchangeRate_TimeExceeded() throws Exception {
        // Setup
        Currency currency1 = new Currency("EGP", "Egyptian Pound", false);
        Currency currency2 = new Currency("RMB", "Renminbi", true);
        currencyDatabase.addCurrency(currency1);
        currencyDatabase.addCurrency(currency2);
        ExchangeRate result = currencyDatabase.getExchangeRate("EGP", "RMB");
        result.timeLastChecked = result.timeLastChecked - 600000;
        // Exercise
        ExchangeRate result2 = currencyDatabase.getExchangeRate("EGP", "RMB");
        // Verify
        assertTrue(result.rate != result2.rate);
        // TearDown
        currencyDatabase.deleteCurrency("EGP");
        currencyDatabase.deleteCurrency("RMB");
    }

    @Test
    public void testAddCurrency() throws Exception {
        // Exercise
        currencyDatabase.addCurrency(new Currency("EGP", "Egyptian Pound", false));
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));
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
        currencyDatabase.addCurrency(new Currency("EGP", "Egyptian Pound", false));
        currencyDatabase.addCurrency(new Currency("RMB", "Renminbi", true));

        List<Currency> testListStart = currencyDatabase.getCurrencies();
        // Exercise
        currencyDatabase.deleteCurrency("EGP");
        // Verify
        assertEquals(1, testListStart.size());
        // TearDown
        currencyDatabase.deleteCurrency("RMB");
    }
}