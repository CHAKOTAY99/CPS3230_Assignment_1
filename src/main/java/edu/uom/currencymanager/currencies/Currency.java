package edu.uom.currencymanager.currencies;

import java.util.StringTokenizer;

public class Currency implements  ICurrency{

    private String code;
    private String name;
    private boolean major;

    private Currency(String code, String name, boolean major) {
        this.code = code;
        this.name = name;
        this.major = major;
    }

    public static Currency createCurrency(String code, String name, boolean major) {
        return new Currency(code, name, major);
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isMajor() {
        return major;
    }

    @Override
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

    @Override
    public String toString() {
        return code + " - " + name;
    }

}