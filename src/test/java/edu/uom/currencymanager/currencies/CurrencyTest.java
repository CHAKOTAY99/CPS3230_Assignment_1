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
        currency = new Currency(code, name, major);
        // Verify
        assertEquals(currency.code, code);
        assertEquals(currency.name, name);
        assertEquals(currency.major, major);
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
        assertEquals(currency.code, code);
        assertEquals(currency.major, major);
        assertEquals(currency.name, name);
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
        assertEquals(testCurrency.code, code);
        assertEquals(testCurrency.major, major);
        assertEquals(testCurrency.name, name);
    }

    @Test
    public void testToString_ReturnValid() {
        // Setup
        currency = new Currency("EGP", "Egyptian Pound", false);
        // Exercise
        String testString = currency.toString();
        // Verify
        assertEquals("EGP - Egyptian Pound", testString);
    }
}
