package edu.uom.currencymanager.currencies;

import edu.uom.currencymanager.RWClass;
import java.util.ArrayList;
import java.util.List;

public interface ICurrencyDatabase {

    public void retreiveData() throws Exception;
    public Currency getCurrencyByCode(String code, List<Currency> currencies);
    public boolean currencyExists(String code, List<Currency> currencies);
    public List<Currency> getCurrencies();
    public List<Currency> getMajorCurrencies();
    public void addCurrency(Currency currency) throws Exception;
    public void deleteCurrency(String code) throws Exception;
}
