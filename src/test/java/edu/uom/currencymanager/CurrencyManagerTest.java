package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CurrencyManagerTest {
    CurrencyManager currencyManager;

    @Before
    public void setup() throws Exception {
        currencyManager = new CurrencyManager();
        // Clean Database
        List<String> currencyCodes = new ArrayList<>();
        List<Currency> initialDatabase = currencyManager.currencyDatabase.getCurrencies();
        for(Currency currency : initialDatabase){
            currencyCodes.add(currency.code);
        }
        for(String currencyCode : currencyCodes){
            currencyManager.currencyDatabase.deleteCurrency(currencyCode);
        }
    }

    @After
    public void teardown() { currencyManager = null; }

    @Test
    public void testGetMajorCurrencyRates_ReturnList() throws Exception{
        currencyManager.addCurrency("EGP", "Egyptian Pound", true);

    }
}
