package edu.uom.currencymanager.currencies;

import java.util.StringTokenizer;

public class Currency implements  ICurrency{

    private String code;
    private String name;
    private boolean major;

    Currency(String code, String name, boolean major) {
        this.code = code;
        this.name = name;
        this.major = major;
    }

    private static Currency createCurrency(String code, String name, boolean major) {
        return new Currency(code, name, major);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMajor() {
        return major;
    }

    public void setMajor(boolean major) {
        this.major = major;
    }

    public static Currency fromString(String currencyString) throws Exception {

        StringTokenizer tokenizer = new StringTokenizer(currencyString,",");

        String code = tokenizer.nextToken();
        String name = tokenizer.nextToken();
        boolean major = tokenizer.nextToken().equalsIgnoreCase("yes");

        return createCurrency(code,name,major);
    }

    public String toString() {
        return code + " - " + name;
    }

}
