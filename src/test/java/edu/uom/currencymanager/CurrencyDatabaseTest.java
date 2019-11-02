package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;
import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CurrencyDatabaseTest {

    CurrencyDatabase currencyDatabase;
    CurrencyServer currencyServer;
    List<Currency> currencies = new ArrayList<Currency>();
    HashMap<String, ExchangeRate> exchangeRates = new HashMap<String, ExchangeRate>();

    String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";

    @Before
    public void setup() throws Exception {
        currencyDatabase = new CurrencyDatabase();
        currencyServer = new DefaultCurrencyServer();
    }

    @After
    public void teardown(){
        currencyDatabase = null;
        currencyServer = null;
    }

}
