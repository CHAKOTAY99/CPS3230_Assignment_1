package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencyserver.CurrencyServer;
import edu.uom.currencymanager.currencyserver.DefaultCurrencyServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RWClass {

    private String currenciesFile = "target" + File.separator + "classes" + File.separator + "currencies.txt";
    private CurrencyDatabase currencyDatabase;
    private CurrencyServer currencyServer;

    public RWClass() throws Exception {
    }


    public List<Currency> readExistingFile() throws Exception {
        //Initialise currency server
        currencyDatabase = new CurrencyDatabase();
        currencyServer = new DefaultCurrencyServer();
        List<Currency> currencies = new ArrayList<Currency>();

        //Read in supported currencies from text file
        BufferedReader reader = new BufferedReader(new FileReader(currenciesFile));

        //skip the first line to avoid header
        String firstLine = reader.readLine();
        if (!firstLine.equals("code,name,major")) {
            throw new Exception("Parsing error when reading currencies file.");
        }

        while (reader.ready()) {
            String  nextLine = reader.readLine();

            //Check if line has 2 commas
            int numCommas = 0;
            char[] chars = nextLine.toCharArray();
            for (char c : chars) {
                if (c == ',') numCommas++;
            }

            if (numCommas != 2) {
                throw new Exception("Parsing error: expected two commas in line " + nextLine);
            }

            Currency currency = Currency.fromString(nextLine);

            if (currency.getCode().length() == 3) {
                if (!currencyDatabase.currencyExists(currency.getCode(), currencies)) {
                    currencies.add(currency);
                }
            } else {
                System.err.println("Invalid currency code detected: " + currency.getCode());
            }
        }
        return currencies;
    }


    public void writeToFile(List<Currency> currencies) throws Exception {

        //Persist list
        BufferedWriter writer = new BufferedWriter(new FileWriter(currenciesFile));

        writer.write("code,name,major\n");
        for (Currency currency : currencies) {
            writer.write(currency.getCode() + "," + currency.getName() + "," + (currency.isMajor() ? "yes" : "no"));
            writer.newLine();
        }

        writer.flush();
        writer.close();
    }

    public String getCurrenciesFile() {
        return currenciesFile;
    }

    public void setCurrenciesFile(String currenciesFile) {
        this.currenciesFile = currenciesFile;
    }

    public CurrencyDatabase getCurrencyDatabase() {
        return currencyDatabase;
    }

    public void setCurrencyDatabase(CurrencyDatabase currencyDatabase) {
        this.currencyDatabase = currencyDatabase;
    }

    public CurrencyServer getCurrencyServer() {
        return currencyServer;
    }

    public void setCurrencyServer(CurrencyServer currencyServer) {
        this.currencyServer = currencyServer;
    }
}
