package edu.uom.currencymanager.currencies;

import java.util.StringTokenizer;

public interface ICurrency {
    public String getCode();
    public void setCode(String code);
    public String getName();
    public void setName(String name);
    public boolean isMajor();
    public void setMajor(boolean major);
    public String toString();

}
