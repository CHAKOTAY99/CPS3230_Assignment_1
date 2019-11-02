package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CurrencyDatabaseTest {

    CurrencyDatabase currencyDatabase;

    @Before
    public void setup() throws Exception {
        currencyDatabase = new CurrencyDatabase();

        List<Currency> initialDatabase = currencyDatabase.getCurrencies();
        if(!initialDatabase.isEmpty()){
            for(Currency currency : initialDatabase){
                currencyDatabase.deleteCurrency(currency.code);
            }
        }
    }

    @After
    public void teardown(){
        currencyDatabase = null;
    }

    @Test
    public void testCurrencyByCode_NotExist(){
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
    public void testCurrencyExists_ReturnFalse(){
        // Exercise
        boolean check = currencyDatabase.currencyExists("RMB");
        // Verify
        assertEquals(false, check);
    }

    @Test
    public void testCurrencyExists_ReturnTrue() throws Exception{
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

//    @Test
//    public void testGetMajorCurrencies_ReturnEmptyDefaultList() throws Exception {
//        // Setup
//    }
}
