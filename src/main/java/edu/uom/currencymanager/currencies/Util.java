package edu.uom.currencymanager.currencies;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;

public class Util {

    static DecimalFormat decimalFormat = new DecimalFormat( "#,###,###,##0.00" );

    public static String formatAmount(double amount) {
        return decimalFormat.format(amount);
    }

}
