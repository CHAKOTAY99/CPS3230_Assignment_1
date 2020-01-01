package edu.uom.currencymanager.currencies;

import java.util.StringTokenizer;

public interface ICurrency {
    private static Currency createCurrency(String code, String name, boolean major) {
        return new Currency(code, name, major);
    }

    public String getCode();

    public void setCode(String code);

    public String getName();

    public void setName(String name);

    public boolean isMajor();

    public void setMajor(boolean major);

    public static Currency fromString(String currencyString) throws Exception {

        StringTokenizer tokenizer = new StringTokenizer(currencyString,",");

        String code = tokenizer.nextToken();
        String name = tokenizer.nextToken();
        boolean major = tokenizer.nextToken().equalsIgnoreCase("yes");

        return createCurrency(code,name,major);
    }

    public String toString();

}
