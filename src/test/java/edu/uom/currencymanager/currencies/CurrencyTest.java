package edu.uom.currencymanager.currencies;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    Currency currency;

    @After
    public void teardown(){
        currency = null;
    }

    @Test
    public void testCreateCurrency(){
        // Setup
        String code = "EGP";
        String name = "Egyptian Pound";
        Boolean major = false;
        // Exercise
        currency = Currency.createCurrency(code, name, major);
        // Verify
        assertEquals(currency.getCode(), code);
        assertEquals(currency.getName(), name);
        assertEquals(currency.isMajor(), major);
    }

    @Test
    public void testFromString_ReturnCurrency() throws Exception {
        String code = "EGP";
        String name = "Egyptian Pound";
        Boolean major = false;
        // Setup
        String currencyString = "EGP,Egyptian Pound,no";
        // Exercise
        Currency currency = Currency.fromString(currencyString);
        // Verify
        assertEquals(currency.getCode(), code);
        assertEquals(currency.isMajor(), major);
        assertEquals(currency.getName(), name);
    }

    @Test
    public void testFromString_ReturnCurrencyTestCase() throws Exception {
        String code = "EGP";
        String name = "Egyptian Pound";
        Boolean major = true;
        // Setup
        String currencyString = "EGP,Egyptian Pound,YES";
        // Exercise
        Currency testCurrency = Currency.fromString(currencyString);
        // Verify
        assertEquals(testCurrency.getCode(), code);
        assertEquals(testCurrency.isMajor(), major);
        assertEquals(testCurrency.getName(), name);
    }

    @Test
    public void testToString_ReturnValid() {
        // Setup
        currency = Currency.createCurrency("EGP", "Egyptian Pound", false);
        // Exercise
        String testString = currency.toString();
        // Verify
        assertEquals("EGP - Egyptian Pound", testString);
    }
}
