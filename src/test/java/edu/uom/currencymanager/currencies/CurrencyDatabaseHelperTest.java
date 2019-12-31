package edu.uom.currencymanager.currencies;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CurrencyDatabaseHelperTest {
    CurrencyDatabase currencyDatabase;

    @Test
    public void testInit_throwExceptionOnFirstLine() throws Exception {
        // Setup
        String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

        // Exercise
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));
        writer.write("ThisIsATest");
        writer.flush();
        writer.close();
        try {
            currencyDatabase = new CurrencyDatabase();
//            currencyDatabase.init();
        } catch (Exception exp) {
            assertEquals("Parsing error when reading currencies file.", exp.getMessage());
        }

        // TearDown
        BufferedWriter teardownWriter = new BufferedWriter(new FileWriter(currenciesFile));
        teardownWriter.write("code,name,major");
        teardownWriter.flush();
        teardownWriter.close();
    }

    @Test
    public void testInit_throwExceptionOnDoubleComma() throws Exception{
        // Setup
        String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

        // Exercise
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));
        writer.write("code,name,major\n");
        writer.write(" EGP,Egyptian Pound,,no\n");
        writer.flush();
        writer.close();
        try {
            currencyDatabase = new CurrencyDatabase();
//            currencyDatabase.init();
        } catch (Exception exp) {
            assertEquals("Parsing error: expected two commas in line  EGP,Egyptian Pound,,no", exp.getMessage());
        }

        // TearDown
        BufferedWriter teardownWriter = new BufferedWriter(new FileWriter(currenciesFile));
        teardownWriter.write("code,name,major");
        teardownWriter.flush();
        teardownWriter.close();
    }

    @Test
    public void testInit_throwExceptionOnIncorrectCurrencyCode() throws Exception{
        // Setup
        String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

        // Exercise
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));
        writer.write("code,name,major\n");
        writer.write(" EGPA,Egyptian Pound,no\n");
        writer.flush();
        writer.close();
        try {
            currencyDatabase = new CurrencyDatabase();
//            currencyDatabase.init();
        } catch (Exception exp) {
            assertEquals("Invalid currency code detected:  EGP,Egyptian Pound,,no", exp.getMessage());
        }

        // TearDown
        BufferedWriter teardownWriter = new BufferedWriter(new FileWriter(currenciesFile));
        teardownWriter.write("code,name,major");
        teardownWriter.flush();
        teardownWriter.close();
    }

    @Test
    public void testInit_NonExistingCode() throws Exception{
        // Setup
        String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

        // Exercise
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));
        writer.write("code,name,major\n");
        writer.write("EGP,Egyptian Pound,no\n");
        writer.flush();
        writer.close();

        currencyDatabase = new CurrencyDatabase();
//        currencyDatabase.init();
        List<Currency> currencies = currencyDatabase.getCurrencies();

        // Verify
        Currency currency = currencyDatabase.getCurrencyByCode("EGP", currencies);
        assertEquals("EGP", currency.getCode());

        // TearDown
        BufferedWriter teardownWriter = new BufferedWriter(new FileWriter(currenciesFile));
        teardownWriter.write("code,name,major");
        teardownWriter.flush();
        teardownWriter.close();
    }
}
