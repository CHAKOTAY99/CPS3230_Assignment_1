package edu.uom.currencymanager.currencies;

import java.util.StringTokenizer;

public class Currency {

    public String code;
    public String name;
    public boolean major;

    public Currency(String code, String name, boolean major) {
        this.code = code;
        this.name = name;
        this.major = major;
    }

    public static Currency fromString(String currencyString) throws Exception {

        StringTokenizer tokenizer = new StringTokenizer(currencyString,",");

        String code = tokenizer.nextToken();
        String name = tokenizer.nextToken();
        boolean major = tokenizer.nextToken().equalsIgnoreCase("yes");

        return new Currency(code,name,major);
    }

    public String toString() {
        return code + " - " + name;
    }

}
