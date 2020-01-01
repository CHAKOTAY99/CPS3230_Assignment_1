package edu.uom.currencymanager.currencies;

import java.text.DecimalFormat;

public class Util {

    static DecimalFormat decimalFormat = new DecimalFormat( "#,###,###,##0.00" );

    static String formatAmount(double amount) {
        return decimalFormat.format(amount);
    }

}
